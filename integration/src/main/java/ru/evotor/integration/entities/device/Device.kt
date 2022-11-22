package ru.evotor.integration.entities.device

import android.os.Parcel
import ru.evotor.integration.utils.KParcelable
import ru.evotor.integration.utils.parcelableCreator
import ru.evotor.integration.utils.readVersioningData
import ru.evotor.integration.utils.writeVersioningData

data class Device(
    val deviceId: String
) : KParcelable {

    override fun writeToParcel(parcel: Parcel, flags: Int) = with(parcel) {
        writeString(deviceId)

        writeVersioningData(VERSION) {}
    }

    companion object {
        private const val VERSION = 1

        @JvmField val CREATOR = parcelableCreator { parcel ->
            with(parcel) {
                val deviceId = readString().toString()

                readVersioningData(VERSION) { dataVersion -> }

                return@with Device(deviceId)
            }
        }
    }
}