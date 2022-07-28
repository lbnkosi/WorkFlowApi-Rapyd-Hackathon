package com.rapyd.toolkit.rapyd_api.models.wallet.personal

data class Contacts(
    val data: List<DataItem>?,
    val total_count: Int = 0,
    val has_more: Boolean = false,
    val url: String = ""
)