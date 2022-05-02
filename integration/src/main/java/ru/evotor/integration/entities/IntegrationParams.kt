package ru.evotor.integration.entities

import android.os.Parcelable

interface IntegrationParams : Parcelable {
    companion object {
        const val KEY_INTEGRATION_PARAMS = "key_integration_params"
    }
}