package ru.evotor.integration.entities

import android.os.Parcelable

interface IntegrationParams : Parcelable {
    companion object {
        const val KEY_INTEGRATION_PARAMS = "key_integration_params"
        const val KEY_RECEIPT_UUID = "RECEIPT_UUID"
        const val KEY_RESULT = "RESULT"
    }
}