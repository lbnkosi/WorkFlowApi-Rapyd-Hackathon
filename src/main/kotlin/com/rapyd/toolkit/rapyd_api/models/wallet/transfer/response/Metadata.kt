package com.rapyd.toolkit.rapyd_api.models.wallet.transfer.response

data class Metadata(
    var merchant_defined: Boolean = false,
    var is_for_order: Boolean = false,
    var order_id: String = ""
)