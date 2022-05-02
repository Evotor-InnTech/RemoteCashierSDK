package ru.evotor.integration.entities.employee.v2

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Employee_V2(
    val employeeId: String
) : Parcelable