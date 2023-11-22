package ru.evotor.integration.entities.credentials

import android.os.Parcel
import ru.evotor.integration.utils.KParcelable
import ru.evotor.integration.utils.parcelableCreator
import ru.evotor.integration.utils.readVersioningData
import ru.evotor.integration.utils.writeVersioningData

data class Credentials(
    val token: String?,
    val userId: String?,
    val inn: String? = null,
    val qrId: Long? = null
) : KParcelable {

    override fun writeToParcel(parcel: Parcel, flags: Int) = with(parcel) {
        writeString(token)
        writeString(userId)
        writeString(inn)
        writeVersioningData(VERSION) {
            qrId?.let { writeLong(it) }
        }
    }

    companion object {
        private const val VERSION = 2

        @JvmField
        val CREATOR = parcelableCreator { parcel ->
            with(parcel) {
                val token = readString()
                val userId = readString()
                val inn = readString()
                var qrId: Long? = null

                readVersioningData(VERSION) { dataVersion ->
                    if (dataVersion >= 2) {
                        qrId = readLong()
                    }
                }

                return@with Credentials(
                    token,
                    userId,
                    inn,
                    qrId
                )
            }
        }
    }
}