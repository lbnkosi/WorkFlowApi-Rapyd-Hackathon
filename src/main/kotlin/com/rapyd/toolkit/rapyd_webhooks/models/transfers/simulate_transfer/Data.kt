package com.rapyd.toolkit.rapyd_webhooks.models.transfers.simulate_transfer

data class Data(
    var amount: String = "",
    var ewallet: String = "",
    var currency: String = "",
    var bank_account: BankAccount = BankAccount(),
    var issued_account_id: String = "",
    var remitter_information: RemitterInformation = RemitterInformation(),
    var issuing_transaction_id: String = ""
)