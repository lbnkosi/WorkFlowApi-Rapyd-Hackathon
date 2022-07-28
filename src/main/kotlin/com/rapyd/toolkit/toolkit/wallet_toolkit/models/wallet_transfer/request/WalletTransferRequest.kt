package com.rapyd.toolkit.toolkit.wallet_toolkit.models.wallet_transfer.request

data class WalletTransferRequest(
    var amount: Int = 0,
    var currency: String = "",
    var source_ewallet: String = "",
    var destination_ewallet: String = "",
    var is_for_order: Boolean = false,
    var order_id: String = ""
)