package com.rapyd.toolkit.rapyd_webhooks.models.transfers.simulate_transfer

data class BankAccount(
    var bic: String = "",
    var zip: String = "",
    var bank: String = "",
    var address: String = "",
    var country: String = "",
    var country_iso: String = "",
    var account_number: String = "",
    var beneficiary_name: String = ""
)