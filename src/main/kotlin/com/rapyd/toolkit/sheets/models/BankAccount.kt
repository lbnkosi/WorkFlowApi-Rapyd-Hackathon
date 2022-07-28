package com.rapyd.toolkit.sheets.models

data class BankAccount(
    var bankName: String? = "",
    var country_iso: String? = "",
    var currency: String? = "",
    var id: String? = "",
    var linked_ewallet: String? = "",
)
