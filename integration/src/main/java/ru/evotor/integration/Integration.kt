package ru.evotor.integration

import androidx.activity.result.ActivityResultRegistry
import ru.evotor.integration.entities.TransactionResult
import ru.evotor.integration.entities.receipt.Receipt_V1

interface Integration {
    fun startPayment(receipt: Receipt_V1)
    fun handlePaymentResult(registry: ActivityResultRegistry, transactionResultHandler: suspend (TransactionResult) -> Unit)
}