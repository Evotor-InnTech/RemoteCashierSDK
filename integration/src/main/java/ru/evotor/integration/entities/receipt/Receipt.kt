package ru.evotor.integration.entities.receipt

import android.os.Parcel
import ru.evotor.integration.entities.receipt.position.Position
import ru.evotor.integration.utils.*
import java.math.BigDecimal

data class Receipt(
    val uuid: String,
    val positions: List<Position>,
    val clientEmail: String?,
    val clientPhone: String?,
    val paymentType: PaymentType,
    val shouldPrintReceipt: Boolean,
    val paymentAddress: String,
    val paymentPlace: String,
    val receiptDiscount: BigDecimal?,
    val mdlp: String? = null,
    val extra: Map<String, String>? = null
) : KParcelable {

    override fun writeToParcel(parcel: Parcel, flags: Int) = with(parcel) {
        writeString(uuid)
        writeTypedList(positions)
        writeString(clientEmail)
        writeString(clientPhone)
        writeEnum(paymentType)
        writeBool(shouldPrintReceipt)
        writeString(paymentAddress)
        writeString(paymentPlace)
        writeBigDecimal(receiptDiscount)
        writeString(mdlp)
        writeStringMap(extra)

        writeVersioningData(VERSION) {}
    }

    companion object {
        private const val VERSION = 1

        @JvmField
        val CREATOR = parcelableCreator { parcel ->
            with(parcel) {
                val uuid = readString().toString()
                val positions = createTypedArrayList(Position.CREATOR)?.toList()
                val clientEmail = readString()
                val clientPhone = readString()
                val paymentType = readEnum<PaymentType>()
                val shouldPrintReceipt = readBool()
                val paymentAddress = readString().toString()
                val paymentPlace = readString().toString()
                val receiptDiscount = readBigDecimal()
                val mdlp = readString()
                val extra = readStringMap()

                readVersioningData(VERSION) { dataVersion -> }

                return@with Receipt(
                    uuid,
                    positions ?: listOf(),
                    clientEmail,
                    clientPhone,
                    paymentType as PaymentType,
                    shouldPrintReceipt,
                    paymentAddress,
                    paymentPlace,
                    receiptDiscount,
                    mdlp,
                    extra
                )
            }
        }
    }
}