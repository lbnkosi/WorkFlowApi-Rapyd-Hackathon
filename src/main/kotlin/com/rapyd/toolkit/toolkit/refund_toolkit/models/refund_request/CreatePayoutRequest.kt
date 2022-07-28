package com.rapyd.toolkit.toolkit.refund_toolkit.models.refund_request

data class CreatePayoutRequest(
    var beneficiary: Beneficiary = Beneficiary(),
    var beneficiary_country: String = "US",
    var beneficiary_entity_type: String = "individual",
    var description: String = "des 1o2ij2oi3j",
    var payout_method_type: String = "us_achnonus_bank",
    var ewallet: String = "",
    var metadata: Metadata = Metadata(),
    var payout_amount: String = "",
    var payout_currency: String = "USD",
    var sender: Sender = Sender(),
    var sender_country: String = "US",
    var sender_currency: String = "USD",
    var sender_entity_type: String = "individual"
)