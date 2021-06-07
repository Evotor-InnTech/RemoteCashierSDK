package ru.evotor.integration.utils

import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.ActivityResultRegistry
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import ru.evotor.integration.entities.TransactionResult
import ru.evotor.integration.entities.receipt.Receipt_V1

class IntegrationRegisterActivityForResult(
    registry: ActivityResultRegistry,
    transactionResultHandler: suspend (transactionResult: TransactionResult) -> Unit
) {

    private companion object {
        const val REGISTRY_KEY = "IntegrationRegisterActivityForResult"
    }

    private val scope = CoroutineScope(Dispatchers.Main)

    private var integrationActivityResultLauncher: ActivityResultLauncher<Receipt_V1> =
        registry.register(
            REGISTRY_KEY,
            IntegrationActivityContract()
        ) { transactionResult ->
            scope.launch { transactionResultHandler(transactionResult) }
        }

    fun openIntegrationActivity(receipt: Receipt_V1) {
        integrationActivityResultLauncher.launch(receipt)
    }
}