package ru.evotor.integration

import androidx.activity.result.ActivityResultRegistry
import ru.evotor.integration.entities.TransactionResult
import ru.evotor.integration.entities.credentials.Credentials
import ru.evotor.integration.entities.device.Device
import ru.evotor.integration.entities.employee.Employee
import ru.evotor.integration.entities.receipt.Receipt

interface Integration {
    fun startSell(
        credentials: Credentials,
        receipt: Receipt,
        device: Device? = null,
        employee: Employee? = null,
        resetAuthorization: Boolean = false
    )
    fun startPayback(
        credentials: Credentials,
        receipt: Receipt,
        sellReceiptUuid: String?,
        device: Device? = null,
        employee: Employee? = null,
        resetAuthorization: Boolean = false,
    )
    fun handlePaymentResult(
        registry: ActivityResultRegistry,
        transactionResultHandler: (TransactionResult) -> Unit
    )
}