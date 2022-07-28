package com.rapyd.toolkit.rapyd_api.models.van.issuebankaccount.response

data class Data(
    val bank_account: BankAccount = BankAccount(),
    val currency: String = "",
    val description: String = "",
    val ewallet: String = "",
    val funding_instructions: String ="",
    val id: String = "",
    val merchant_reference_id: String = "",
    val metadata: Metadata = Metadata(),
    val status: String = "",
    val transactions: List<Any>
)