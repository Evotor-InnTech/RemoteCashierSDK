package ru.evotor.integration.entities.receipt.position

import android.os.Parcel
import ru.evotor.integration.utils.*
import java.math.BigDecimal

data class Position(
    val price: BigDecimal,
    val name: String,
    val measureName: String,
    val quantity: BigDecimal,
    val tax: Tax?,
    val commodityId: String? = null,
    val type: Type?,
    val priceWithDiscount: BigDecimal?,
    val mark: String?,
    val settlementMethodType: SettlementMethodType = SettlementMethodType.FULL
) : KParcelable {

    override fun writeToParcel(parcel: Parcel, flags: Int) = with(parcel) {
        writeBigDecimal(price)
        writeString(name)
        writeString(measureName)
        writeBigDecimal(quantity)
        writeEnum(tax)
        writeString(commodityId)
        writeEnum(type)
        writeBigDecimal(priceWithDiscount)
        writeString(mark)
        writeEnum(settlementMethodType)

        writeVersioningData(VERSION) {}
    }

    companion object {
        private const val VERSION = 1

        @JvmField val CREATOR = parcelableCreator { parcel ->
            with(parcel) {
                val price = readBigDecimal()
                val name = readString().toString()
                val measureName = readString().toString()
                val quantity = readBigDecimal()
                val tax = readEnum<Tax>()
                val commodityId = readString()
                val type = readEnum<Type>()
                val priceWithDiscount = readBigDecimal()
                val mark = readString()
                val settlementMethodType = readEnum<SettlementMethodType>()

                readVersioningData(VERSION) { dataVersion -> }

                return@with Position(
                    price as BigDecimal,
                    name,
                    measureName,
                    quantity as BigDecimal,
                    tax,
                    commodityId,
                    type,
                    priceWithDiscount,
                    mark,
                    settlementMethodType as SettlementMethodType
                )
            }
        }
    }
}