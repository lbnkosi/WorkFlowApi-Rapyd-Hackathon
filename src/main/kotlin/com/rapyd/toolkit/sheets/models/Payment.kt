package com.rapyd.toolkit.sheets.models

data class Payment(
    var paymentAmount: String = "",
    var paymentComplete: String = "",
    var paymentIssuingId: String = "",
    var paymentIsDependentPayment: String = "",
    var paymentAmountRemaining: String = "",
    var paymentAmountDepositedSoFar: String = "",
)
