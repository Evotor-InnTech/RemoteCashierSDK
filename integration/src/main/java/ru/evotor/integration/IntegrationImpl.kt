package ru.evotor.integration

import androidx.activity.result.ActivityResultRegistry
import ru.evotor.integration.entities.TransactionResult
import ru.evotor.integration.entities.receipt.Receipt_V1
import ru.evotor.integration.utils.IntegrationRegisterActivityForResult

class IntegrationImpl : Integration {

    private var integrationRegisterActivityForResult: IntegrationRegisterActivityForResult? = null

    override fun startPayment(receipt: Receipt_V1) {
        integrationRegisterActivityForResult?.openIntegrationActivity(receipt)
    }

    override fun handlePaymentResult(
        registry: ActivityResultRegistry,
        transactionResultHandler: suspend (TransactionResult) -> Unit
    ) {
        integrationRegisterActivityForResult =
            IntegrationRegisterActivityForResult(registry, transactionResultHandler)
    }
}