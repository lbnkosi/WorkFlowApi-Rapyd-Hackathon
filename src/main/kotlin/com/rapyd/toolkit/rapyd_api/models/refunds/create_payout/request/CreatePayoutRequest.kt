package com.rapyd.toolkit.rapyd_api.models.refunds.create_payout.request

data class CreatePayoutRequest(
    var beneficiary: Beneficiary = Beneficiary(),
    var beneficiary_country: String = "",
    var beneficiary_entity_type: String = "",
    var description: String = "",
    var payout_method_type: String = "",
    var ewallet: String = "",
    var metadata: Metadata = Metadata(),
    var payout_amount: String = "",
    var payout_currency: String = "",
    var sender: Sender = Sender(),
    var sender_country: String = "",
    var sender_currency: String = "",
    var sender_entity_type: String = ""
)