package com.rapyd.toolkit.rapyd_api.models.refunds.bank_transfer_refund.request

data class BankTransferPayoutRequest(
    var beneficiary: String = "",
    var beneficiary_country: String = "",
    var beneficiary_entity_type: String = "",
    var description: String = "",
    var merchant_reference_id: String = "",
    var ewallet: String = "",
    var payout_amount: String = "",
    var payout_currency: String = "",
    var payout_method_type: String = "",
    var sender: String = "",
    var sender_country: String = "",
    var sender_currency: String = "",
    var sender_entity_type: String = "",
    var statement_descriptor: String = "",
    var metadata: Metadata = Metadata()
)