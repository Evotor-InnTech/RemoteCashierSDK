package ru.evotor.integration

import androidx.activity.result.ActivityResultRegistry
import ru.evotor.integration.entities.TransactionResult
import ru.evotor.integration.entities.credentials.v2.Credentials_V2
import ru.evotor.integration.entities.device.v2.Device_V2
import ru.evotor.integration.entities.employee.v2.Employee_V2
import ru.evotor.integration.entities.receipt.v2.Receipt_V2

interface Integration {
    fun startSellV2(
        credentials: Credentials_V2,
        receipt: Receipt_V2,
        device: Device_V2? = null,
        employee: Employee_V2? = null,
        resetAuthorization: Boolean = false
    )
    fun startPaybackV2(
        credentials: Credentials_V2,
        receipt: Receipt_V2,
        sellReceiptUuid: String?,
        device: Device_V2? = null,
        employee: Employee_V2? = null,
        resetAuthorization: Boolean = false,
    )
    fun handlePaymentResult(
        registry: ActivityResultRegistry,
        transactionResultHandler: (TransactionResult) -> Unit
    )
}