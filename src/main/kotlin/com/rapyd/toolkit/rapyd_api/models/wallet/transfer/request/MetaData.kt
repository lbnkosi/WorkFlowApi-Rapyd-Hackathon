package com.rapyd.toolkit.rapyd_api.models.wallet.transfer.request

data class MetaData(
    var merchant_defined: Boolean = false,
    var is_for_order: Boolean = false,
    var order_id: String = ""
)