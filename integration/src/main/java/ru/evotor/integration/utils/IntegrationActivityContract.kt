package ru.evotor.integration.utils

import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.activity.result.contract.ActivityResultContract
import ru.evotor.integration.entities.*

class IntegrationActivityContract : ActivityResultContract<IntegrationParams, TransactionResult>() {

    private companion object {
        const val INTENT_ACTION = "ru.evotor.evotormobile.REQUEST_RESPONSE"
    }

    override fun createIntent(context: Context, integrationParams: IntegrationParams): Intent =
        Intent(INTENT_ACTION).putExtra(IntegrationParams.KEY_INTEGRATION_PARAMS, integrationParams)

    override fun parseResult(resultCode: Int, intent: Intent?): TransactionResult =
        TransactionResult(
            receiptUuid = intent?.getStringExtra(IntegrationParams.KEY_RECEIPT_UUID).orEmpty(),
            operationResult = OperationResult(
                success = resultCode == Activity.RESULT_OK,
                message = intent?.getStringExtra(IntegrationParams.KEY_RESULT).orEmpty()
            )
        )
}