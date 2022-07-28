package com.rapyd.toolkit.toolkit.payment_toolkit.models.van

data class CreateNewVanRequest(
    var country: String = "",
    var currency: String = "",
    var customerID: String = "",
    var customerEmail: String = "",
    var description: String = "",
)
