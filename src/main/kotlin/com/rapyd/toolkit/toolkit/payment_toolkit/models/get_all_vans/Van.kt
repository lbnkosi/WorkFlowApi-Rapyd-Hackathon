package com.rapyd.toolkit.toolkit.payment_toolkit.models.get_all_vans


data class Van(
    var bank: String? = "",
    var country_iso: String? = "",
    var currency: String? = "",
    var id: String? = "",
    var linked_ewallet: String? = "",
    var parent_ewallet: String? = "",
    var parent_id: String? = "",
)
