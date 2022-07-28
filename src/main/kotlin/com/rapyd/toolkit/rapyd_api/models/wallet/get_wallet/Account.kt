package com.rapyd.toolkit.rapyd_api.models.wallet.get_wallet

data class Account(
    var id: String = "",
    var currency: String = "",
    var alias: String = "",
    var balance: Int = 0,
    var received_balance: Int = 0,
    var on_hold_balance: Int = 0,
    var reserve_balance: Int = 0,
    var limits: Any = Any(),
    var limit: Any = Any()
)