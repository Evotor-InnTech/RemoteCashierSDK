package ru.evotor.integration.entities

import android.os.Parcel
import ru.evotor.integration.BuildConfig
import ru.evotor.integration.entities.credentials.Credentials
import ru.evotor.integration.entities.device.Device
import ru.evotor.integration.entities.employee.Employee
import ru.evotor.integration.entities.receipt.OperationType
import ru.evotor.integration.entities.receipt.Receipt
import ru.evotor.integration.utils.*

data class IntegrationData(
    val credentials: Credentials,
    val receipt: Receipt,
    val sellReceiptUuid: String? = null,
    val operationType: OperationType,
    val device: Device?,
    val employee: Employee?,
    val resetAuthorization: Boolean,
    val integrationVersion: String = BuildConfig.VERSION_NAME
) : IntegrationParams {

    override fun writeToParcel(parcel: Parcel, flags: Int) = with(parcel) {
        writeTypedObject(credentials, flags)
        writeTypedObject(receipt, flags)
        writeString(sellReceiptUuid)
        writeEnum(operationType)
        writeTypedObject(device, flags)
        writeTypedObject(employee, flags)
        writeBool(resetAuthorization)
        writeString(integrationVersion)

        writeVersioningData(VERSION) {}
    }

    companion object {
        private const val VERSION = 1

        @JvmField
        val CREATOR = parcelableCreator { parcel ->
            with(parcel) {
                val credentials = readTypedObject(Credentials.CREATOR)
                val receipt = readTypedObject(Receipt.CREATOR)
                val sellReceiptUuid = readString()
                val operationType = readEnum<OperationType>()
                val device = readTypedObject(Device.CREATOR)
                val employee = readTypedObject(Employee.CREATOR)
                val resetAuthorization = readBool()
                val integrationVersion = readString().toString()

                readVersioningData(VERSION) { dataVersion -> }

                return@with IntegrationData(
                    credentials as Credentials,
                    receipt as Receipt,
                    sellReceiptUuid,
                    operationType as OperationType,
                    device,
                    employee,
                    resetAuthorization,
                    integrationVersion
                )
            }
        }
    }
}