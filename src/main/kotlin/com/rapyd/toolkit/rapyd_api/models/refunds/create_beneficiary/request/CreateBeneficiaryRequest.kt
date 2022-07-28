package com.rapyd.toolkit.rapyd_api.models.refunds.create_beneficiary.request

data class CreateBeneficiaryRequest(
    var category: String = "",
    var sender_entity_type: String = "",
    var sender_country: String = "",
    var merchant_reference_id: String = "",
    var beneficiary_country: String = "",
    var beneficiary_entity_type: String = "",
    var beneficiary_optional_fields: BeneficiaryOptionalFields = BeneficiaryOptionalFields()
)