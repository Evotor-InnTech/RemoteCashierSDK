package ru.evotor.integration.entities

import kotlinx.android.parcel.Parcelize
import ru.evotor.integration.entities.credentials.v2.Credentials_V2
import ru.evotor.integration.entities.device.v2.Device_V2
import ru.evotor.integration.entities.employee.v2.Employee_V2
import ru.evotor.integration.entities.receipt.OperationType_V1
import ru.evotor.integration.entities.receipt.v2.Receipt_V2

@Parcelize
data class IntegrationParamsV2(
    val credentials: Credentials_V2,
    val receipt: Receipt_V2,
    val sellReceiptUuid: String? = null,
    val operationType: OperationType_V1,
    val device: Device_V2?,
    val employee: Employee_V2?,
    val resetAuthorization: Boolean
) : IntegrationParams