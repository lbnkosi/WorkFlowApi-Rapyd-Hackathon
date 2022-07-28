package com.rapyd.toolkit.rapyd_webhooks.models.transfers.simulate_transfer

data class RemitterInformation(
    var name: String = "",
    var bank_code: String = "",
    var account_id: String = ""
)