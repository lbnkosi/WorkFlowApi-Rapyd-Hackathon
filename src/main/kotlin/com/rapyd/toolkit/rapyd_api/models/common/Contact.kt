package com.rapyd.toolkit.rapyd_api.models.common

import com.rapyd.toolkit.rapyd_api.models.wallet.company.BusinessDetails

data class Contact(
    var country: String = "",
    var metadata: Metadata = Metadata(),
    var address: Address = Address(),
    var identification_number: String = "",
    var date_of_birth: String = "11/22/1900",
    var last_name: String = "",
    var identification_type: String = "PA",
    var contact_type: String = "personal",
    var mothers_name: String = "DEFAULT",
    var nationality: String = "",
    var phone_number: String = "",
    var first_name: String = "",
    var email: String = "",
    var business_details: BusinessDetails? = BusinessDetails(),
)