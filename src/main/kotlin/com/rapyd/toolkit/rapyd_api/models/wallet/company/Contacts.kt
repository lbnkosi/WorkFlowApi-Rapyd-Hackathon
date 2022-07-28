package com.rapyd.toolkit.rapyd_api.models.wallet.company

data class Contacts(
    val data: List<DataItem>?,
    val totalCount: Int = 0,
    val hasMore: Boolean = false,
    val url: String = ""
)