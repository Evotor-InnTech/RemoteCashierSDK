package ru.evotor.integration

import androidx.activity.result.ActivityResultRegistry
import ru.evotor.integration.entities.*
import ru.evotor.integration.entities.credentials.Credentials
import ru.evotor.integration.entities.device.Device
import ru.evotor.integration.entities.employee.Employee
import ru.evotor.integration.entities.receipt.OperationType
import ru.evotor.integration.entities.receipt.Receipt
import ru.evotor.integration.utils.IntegrationRegisterActivityForResult

class IntegrationImpl : Integration {

    private var integrationRegisterActivityForResult: IntegrationRegisterActivityForResult? = null

    override fun startSell(
        credentials: Credentials,
        receipt: Receipt,
        device: Device?,
        employee: Employee?,
        resetAuthorization: Boolean
    ) {
        val integrationData = IntegrationData(
            credentials = credentials,
            receipt = receipt,
            operationType = OperationType.SELL,
            device = device,
            employee = employee,
            resetAuthorization = resetAuthorization,
        )
        integrationRegisterActivityForResult?.openIntegrationActivity(integrationData)
    }

    override fun startPayback(
        credentials: Credentials,
        receipt: Receipt,
        sellReceiptUuid: String?,
        device: Device?,
        employee: Employee?,
        resetAuthorization: Boolean
    ) {
        val integrationData = IntegrationData(
            credentials = credentials,
            receipt = receipt,
            sellReceiptUuid = sellReceiptUuid,
            operationType = OperationType.PAYBACK,
            device = device,
            employee = employee,
            resetAuthorization = resetAuthorization,
        )
        integrationRegisterActivityForResult?.openIntegrationActivity(integrationData)
    }

    override fun handlePaymentResult(
        registry: ActivityResultRegistry,
        transactionResultHandler: (TransactionResult) -> Unit
    ) {
        integrationRegisterActivityForResult =
            IntegrationRegisterActivityForResult(registry, transactionResultHandler)
    }
}