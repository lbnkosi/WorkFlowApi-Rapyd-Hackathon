package com.rapyd.toolkit.rapyd_api.models.refunds.card_payout.request

data class CardPayoutRequest(
    var ewallet: String = "",
    var payout_amount: Int = 0,
    var payout_method_type: String = "",
    var sender_currency: String = "",
    var sender_country: String = "",
    var beneficiary: Beneficiary = Beneficiary(),
    var beneficiary_country: String = "",
    var payout_currency: String = "",
    var sender_entity_type: String = "",
    var beneficiary_entity_type: String = "",
    var sender: Sender = Sender(),
    var description: String = "",
    var statement_descriptor: String = ""
)