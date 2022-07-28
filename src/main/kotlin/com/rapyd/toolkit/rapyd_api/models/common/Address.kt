package com.rapyd.toolkit.rapyd_api.models.common

data class Address(
    var line_1: String = "DEFAULT",
    var zip: String = "DEFAULT",
    var line_2: String = "DEFAULT",
    var country: String = "",
    var metadata: Metadata = Metadata(),
    var city: String = "DEFAULT",
    var district: String = "DEFAULT",
    var name: String = "",
    var canton: String = "DEFAULT",
    var phone_number: String = "",
    var state: String = "DEFAULT",
    var line_3: String = "DEFAULT",
    var createdAt: Int = 0,
    var id: String = "",
)