package com.rapyd.toolkit.rapyd_api.models.countries

data class DataItem(
    val iso_alpha2: String = "",
    val iso_alpha3: String = "",
    val currency_name: String = "",
    val currency_sign: String = "",
    val name: String = "",
    val id: Int = 0,
    val currency_code: String = "",
    val phone_code: String = ""
)