package ru.evotor.integration.entities

data class TransactionResult(
    val receiptUuid: String,
    val operationResult: OperationResult
)