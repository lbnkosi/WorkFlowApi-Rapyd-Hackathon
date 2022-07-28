package com.rapyd.toolkit.toolkit.refund_toolkit.models.general

data class CreateBeneficiaryRequest(
    var category: String = "",
    var beneficiaryCountry: String = "",
    var customerId: String = ""
)
