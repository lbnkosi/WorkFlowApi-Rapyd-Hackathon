package com.rapyd.toolkit.rapyd_api.models.refunds.cash_payout.request

data class CashPayoutRequest(
    var ewallet: String = "",
    var merchant_reference_id: String = "",
    var payout_amount: Int = 0,
    var payout_method_type: String = "",
    var sender_currency: String = "",
    var sender_country: String = "",
    var beneficiary_country: String = "",
    var payout_currency: String = "",
    var sender_entity_type: String = "",
    var beneficiary_entity_type: String = "",
    var beneficiary: String = "",
    var sender: String = "",
    var description: String = "",
    var statement_descriptor: String = "",
    var metadata: Metadata = Metadata()
)