package com.rapyd.toolkit.rapyd_api.models.refunds.create_beneficiary.response

data class BeneficiaryOptionalFields(
    var last_name: String = "",
    var first_name: String = "",
    var company_name: Any = Any(),
    var identification_type: String = "",
    var identification_value: String = ""
)