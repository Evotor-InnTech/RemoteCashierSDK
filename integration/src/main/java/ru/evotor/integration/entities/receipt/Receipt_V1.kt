package ru.evotor.integration.entities.receipt

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import ru.evotor.integration.entities.receipt.position.Position_V1
import java.math.BigDecimal
import java.util.*

@Parcelize
data class Receipt_V1(
    val uuid: String,
    val positions: List<Position_V1>,
    var operationType: OperationType_V1 = OperationType_V1.SELL,
    var paymentPlace: String? = null,
    var paymentAddress: String? = null,
    var receiptDiscount: BigDecimal? = null,
    val tapOnPhoneRefId: String? = null,
    val creationDate: Date? = null,
    val extra: String? = null
) : Parcelable