package ru.evotor.integration.entities.credentials

import android.os.Parcel
import ru.evotor.integration.utils.KParcelable
import ru.evotor.integration.utils.parcelableCreator
import ru.evotor.integration.utils.readVersioningData
import ru.evotor.integration.utils.writeVersioningData

data class Credentials(
    val token: String,
    val userId: String,
    val inn: String? = null
) : KParcelable {

    override fun writeToParcel(parcel: Parcel, flags: Int) = with(parcel) {
        writeString(token)
        writeString(userId)
        writeString(inn)

        writeVersioningData(VERSION) {}
    }

    companion object {
        private const val VERSION = 1

        @JvmField val CREATOR = parcelableCreator { parcel ->
            with(parcel) {
                val token = readString().toString()
                val userId = readString().toString()
                val inn = readString()

                readVersioningData(VERSION) { dataVersion -> }

                return@with Credentials(
                    token,
                    userId,
                    inn
                )
            }
        }
    }
}