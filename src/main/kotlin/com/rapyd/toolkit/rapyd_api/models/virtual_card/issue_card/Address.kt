package com.rapyd.toolkit.rapyd_api.models.virtual_card.issue_card

data class Address(
    val line_1: String = "",
    val zip: String = "",
    val line_2: String = "",
    val country: String = "",
    val metadata: Metadata = Metadata(),
    val city: String = "",
    val created_at: Int = 0,
    val line_3: String = "",
    val district: String = "",
    val name: String = "",
    val canton: String = "",
    val phone_number: String = "",
    val id: String = "",
    val state: String = ""
)