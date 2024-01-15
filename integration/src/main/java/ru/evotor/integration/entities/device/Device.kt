package ru.evotor.integration.entities.device

import android.os.Parcel
import ru.evotor.integration.utils.KParcelable
import ru.evotor.integration.utils.parcelableCreator
import ru.evotor.integration.utils.readVersioningData
import ru.evotor.integration.utils.writeVersioningData

data class Device(
    val deviceId: String,
    val qrId: Long? = null
) : KParcelable {

    override fun writeToParcel(parcel: Parcel, flags: Int) = with(parcel) {
        writeString(deviceId)
        writeVersioningData(VERSION) {
            parcel.writeValue(qrId)
        }
    }

    companion object {
        private const val VERSION = 3

        @JvmField
        val CREATOR = parcelableCreator { parcel ->
            with(parcel) {
                val deviceId = readString().toString()
                var qrId: Long? = null

                readVersioningData(VERSION) { dataVersion ->
                    if (dataVersion == 2) {
                        qrId = readLong()
                    }
                    if (dataVersion >= 3) {
                        qrId = parcel.readValue(Long::class.java.classLoader) as? Long
                    }
                }

                return@with Device(deviceId, qrId)
            }
        }
    }
}