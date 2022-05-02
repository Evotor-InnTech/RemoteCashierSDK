package ru.evotor.integration.utils

import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.activity.result.contract.ActivityResultContract
import ru.evotor.integration.entities.*

class IntegrationActivityContract : ActivityResultContract<IntegrationParams, TransactionResult>() {

    private companion object {
        const val INTENT_ACTION = "ru.evotor.evotormobile.REQUEST_RESPONSE"
        const val KEY_RECEIPT_UUID = "RECEIPT_UUID"
        const val KEY_RESULT = "RESULT"
    }

    override fun createIntent(context: Context, integrationParams: IntegrationParams): Intent =
        Intent(INTENT_ACTION).putExtra(IntegrationParams.KEY_INTEGRATION_PARAMS, integrationParams)

    override fun parseResult(resultCode: Int, intent: Intent?): TransactionResult =
        TransactionResult(
            receiptUuid = intent?.getStringExtra(KEY_RECEIPT_UUID).orEmpty(),
            operationResult = OperationResult(
                success = resultCode == Activity.RESULT_OK,
                message = intent?.getStringExtra(KEY_RESULT).orEmpty()
            )
        )
}