package ru.evotor.integration.entities.receipt.v2

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import ru.evotor.integration.entities.receipt.OperationType_V1
import ru.evotor.integration.entities.receipt.position.Position_V1
import java.math.BigDecimal

@Parcelize
data class Receipt_V2(
    val uuid: String,
    val positions: List<Position_V1>,
    val clientEmail: String?,
    val clientPhone: String?,
    val paymentType: PaymentType_V2,
    val shouldPrintReceipt: Boolean,
    val paymentAddress: String,
    val paymentPlace: String,
    val receiptDiscount: BigDecimal?
) : Parcelable