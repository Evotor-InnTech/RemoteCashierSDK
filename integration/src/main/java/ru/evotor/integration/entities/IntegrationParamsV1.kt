package ru.evotor.integration.entities

import kotlinx.android.parcel.Parcelize
import ru.evotor.integration.entities.receipt.Receipt_V1

@Parcelize
data class IntegrationParamsV1(
    val receipt: Receipt_V1
) : IntegrationParams