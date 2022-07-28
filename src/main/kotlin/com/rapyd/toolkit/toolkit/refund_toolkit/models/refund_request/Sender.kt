package com.rapyd.toolkit.toolkit.refund_toolkit.models.refund_request

data class Sender(
    var city: String = "Montana",
    var state: String = "State",
    var postcode: String = "12345",
    var description: String = "Description",
    var first_name: String = "Bongani",
    var last_name: String = "Nkosi",
    var identification_type: String = "international_passport",
    var identification_value: String = "9908205984594",
    var phone_number: String = "621212938122",
    var occupation: String = "accountant",
    var source_of_income: String = "salary",
    var date_of_birth: String = "12/12/1980",
    var address: String = "1 Main Street",
    var purpose_code: String = "salary",
    var beneficiary_relationship: String = "customer"
)