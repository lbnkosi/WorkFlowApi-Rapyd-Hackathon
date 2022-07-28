package com.rapyd.toolkit.rapyd_api.models.customer.create_customer.request

data class Fields(
    var cvv: String = "",
    var expiration_month: String = "",
    var expiration_year: String = "",
    var number: String = ""
)