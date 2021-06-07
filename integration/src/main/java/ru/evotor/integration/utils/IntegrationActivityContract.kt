package ru.evotor.integration.utils

import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.activity.result.contract.ActivityResultContract
import ru.evotor.integration.entities.TransactionResult
import ru.evotor.integration.entities.OperationResult
import ru.evotor.integration.entities.receipt.Receipt_V1
import ru.evotor.integration.utils.IntegrationParams.KEY_RECEIPT_UUID
import ru.evotor.integration.utils.IntegrationParams.KEY_RESULT
import ru.evotor.integration.utils.IntegrationParams.RECEIPT

class IntegrationActivityContract : ActivityResultContract<Receipt_V1, TransactionResult>() {

    private companion object {
        const val INTENT_ACTION = "ru.evotor.evotormobile.REQUEST_RESPONSE"
    }

    override fun createIntent(context: Context, receipt: Receipt_V1?): Intent =
        Intent(INTENT_ACTION).putExtra(RECEIPT, receipt)

    override fun parseResult(resultCode: Int, intent: Intent?): TransactionResult =
        TransactionResult(
            receiptUuid = intent?.getStringExtra(KEY_RECEIPT_UUID).orEmpty(),
            operationResult = OperationResult(
                success = resultCode == Activity.RESULT_OK,
                message = intent?.getStringExtra(KEY_RESULT).orEmpty()
            )
        )
}