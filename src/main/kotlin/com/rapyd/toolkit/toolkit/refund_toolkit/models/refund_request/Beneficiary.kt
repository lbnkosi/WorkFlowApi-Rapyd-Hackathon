package com.rapyd.toolkit.toolkit.refund_toolkit.models.refund_request

data class Beneficiary(
    var payment_type: String = "regular",
    var address: String = "1 Main Street",
    var city: String = "Anytown",
    var country: String = "US",
    var first_name: String = "Lebogang",
    var last_name: String = "Nkosi",
    var state: String = "NY",
    var postcode: String = "10101",
    var aba: String = "573675777",
    var account_number: String = "77711020345678"
)