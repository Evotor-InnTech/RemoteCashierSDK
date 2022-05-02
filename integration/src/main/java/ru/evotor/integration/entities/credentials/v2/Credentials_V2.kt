package ru.evotor.integration.entities.credentials.v2

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Credentials_V2(
    val token: String,
    val userId: String,
    val inn: String
) : Parcelable