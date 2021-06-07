package ru.evotor.integration.entities.receipt.position

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import java.math.BigDecimal

@Parcelize
data class Position_V1(
    val price: BigDecimal,
    val name: String,
    val measureName: String,
    val quantity: BigDecimal,
    val tax: String?,
    val commodityId: String? = null,
    val type: Type_V1?,
    val priceWithDiscount: BigDecimal?
) : Parcelable