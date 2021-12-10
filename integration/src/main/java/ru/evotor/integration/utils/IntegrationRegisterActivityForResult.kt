package ru.evotor.integration.utils

import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.ActivityResultRegistry
import ru.evotor.integration.entities.TransactionResult
import ru.evotor.integration.entities.receipt.Receipt_V1

class IntegrationRegisterActivityForResult(
    registry: ActivityResultRegistry,
    transactionResultHandler: (transactionResult: TransactionResult) -> Unit
) {

    private companion object {
        const val REGISTRY_KEY = "IntegrationRegisterActivityForResult"
    }

    private var integrationActivityResultLauncher: ActivityResultLauncher<Receipt_V1> =
        registry.register(
            REGISTRY_KEY,
            IntegrationActivityContract(),
            transactionResultHandler
        )

    fun openIntegrationActivity(receipt: Receipt_V1) {
        integrationActivityResultLauncher.launch(receipt)
    }
}