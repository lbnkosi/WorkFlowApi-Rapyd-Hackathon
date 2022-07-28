package com.rapyd.toolkit.rapyd_api.models.customer.fetch_customer.response

data class IssuedBankAccount(
    var bank: String? = "",
    var country_iso: String? = "",
    var currency: String? = "",
    var id: String? = "",
    var linked_ewallet: String? = ""
)