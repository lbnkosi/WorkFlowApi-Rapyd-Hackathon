package com.rapyd.toolkit.rapyd_api.models.van.get_bank_account

data class BankAccount(
    var beneficiary_name: String = "",
    var address: String = "",
    var country_iso: String = "",
    var iban: String = "",
    var sort_code: String = "",
    var account_no: String = "",
    var bic: String = ""
)