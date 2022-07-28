package com.rapyd.toolkit.rapyd_api.models.van.issuebankaccount.response

data class BankAccount(
    val account_no: String = "",
    val address: String = "",
    val bank: String = "",
    val beneficiary_name: String = "",
    val bic: String = "",
    val country: String = "",
    val country_iso: String = "",
    val iban: String = "",
    val sort_code: String = "",
    val zip: String = ""
)