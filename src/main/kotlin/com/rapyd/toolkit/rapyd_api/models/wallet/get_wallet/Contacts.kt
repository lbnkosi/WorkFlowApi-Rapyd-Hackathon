package com.rapyd.toolkit.rapyd_api.models.wallet.get_wallet

data class Contacts(
    var `data`: List<DataX> = listOf(),
    var has_more: Boolean = false,
    var total_count: Int = 0,
    var url: String = ""
)