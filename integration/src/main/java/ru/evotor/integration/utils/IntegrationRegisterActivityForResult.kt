package ru.evotor.integration.utils

import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.ActivityResultRegistry
import ru.evotor.integration.entities.IntegrationParams
import ru.evotor.integration.entities.TransactionResult

class IntegrationRegisterActivityForResult(
    registry: ActivityResultRegistry,
    transactionResultHandler: (TransactionResult) -> Unit
) {

    private companion object {
        const val REGISTRY_KEY = "IntegrationRegisterActivityForResult"
    }

    private var integrationActivityResultLauncher: ActivityResultLauncher<IntegrationParams> =
        registry.register(
            REGISTRY_KEY,
            IntegrationActivityContract(),
            transactionResultHandler
        )

    fun openIntegrationActivity(integrationParams: IntegrationParams) {
        integrationActivityResultLauncher.launch(integrationParams)
    }
}