package ru.evotor.integration

import androidx.activity.result.ActivityResultRegistry
import ru.evotor.integration.entities.*
import ru.evotor.integration.entities.credentials.v2.Credentials_V2
import ru.evotor.integration.entities.device.v2.Device_V2
import ru.evotor.integration.entities.employee.v2.Employee_V2
import ru.evotor.integration.entities.receipt.OperationType_V1
import ru.evotor.integration.entities.receipt.Receipt_V1
import ru.evotor.integration.entities.receipt.v2.Receipt_V2
import ru.evotor.integration.utils.IntegrationRegisterActivityForResult

class IntegrationImpl : Integration {

    private var integrationRegisterActivityForResult: IntegrationRegisterActivityForResult? = null

    override fun startPaymentV1(receipt: Receipt_V1) {
        val integrationParams = IntegrationParamsV1(receipt)
        integrationRegisterActivityForResult?.openIntegrationActivity(integrationParams)
    }

    override fun startSellV2(
        credentials: Credentials_V2,
        receipt: Receipt_V2,
        device: Device_V2?,
        employee: Employee_V2?,
        resetAuthorization: Boolean
    ) {
        val integrationParams = IntegrationParamsV2(
            credentials = credentials,
            receipt = receipt,
            operationType = OperationType_V1.SELL,
            device = device,
            employee = employee,
            resetAuthorization = resetAuthorization,
        )
        integrationRegisterActivityForResult?.openIntegrationActivity(integrationParams)
    }

    override fun startPaybackV2(
        credentials: Credentials_V2,
        receipt: Receipt_V2,
        sellReceiptUuid: String?,
        device: Device_V2?,
        employee: Employee_V2?,
        resetAuthorization: Boolean
    ) {
        val integrationParams = IntegrationParamsV2(
            credentials = credentials,
            receipt = receipt,
            sellReceiptUuid = sellReceiptUuid,
            operationType = OperationType_V1.PAYBACK,
            device = device,
            employee = employee,
            resetAuthorization = resetAuthorization,
        )
        integrationRegisterActivityForResult?.openIntegrationActivity(integrationParams)
    }

    override fun handlePaymentResult(
        registry: ActivityResultRegistry,
        transactionResultHandler: (TransactionResult) -> Unit
    ) {
        integrationRegisterActivityForResult =
            IntegrationRegisterActivityForResult(registry, transactionResultHandler)
    }
}