package com.rapyd.toolkit.rapyd_api.models.van.issuebankaccount.request

data class IssueBankAccountRequest(
    var country: String = "",
    var currency: String = "",
    var description: String = "",
    var ewallet: String = "",
    var merchant_reference_id: String = "",
    var metadata: Metadata = Metadata()
)