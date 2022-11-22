package ru.evotor.integration.entities.employee

import android.os.Parcel
import ru.evotor.integration.utils.KParcelable
import ru.evotor.integration.utils.parcelableCreator
import ru.evotor.integration.utils.readVersioningData
import ru.evotor.integration.utils.writeVersioningData

data class Employee(
    val employeeId: String
) : KParcelable {

    override fun writeToParcel(parcel: Parcel, flags: Int) = with(parcel) {
        writeString(employeeId)

        writeVersioningData(VERSION) {}
    }

    companion object {
        private const val VERSION = 1

        @JvmField val CREATOR = parcelableCreator { parcel ->
            with(parcel) {
                val employeeId = readString().toString()

                readVersioningData(VERSION) { dataVersion -> }

                return@with Employee(employeeId)
            }
        }
    }
}