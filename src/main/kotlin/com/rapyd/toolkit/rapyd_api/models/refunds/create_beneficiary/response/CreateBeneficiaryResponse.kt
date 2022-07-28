package com.rapyd.toolkit.rapyd_api.models.refunds.create_beneficiary.response

data class CreateBeneficiaryResponse(
    var status: Status = Status(),
    var `data`: Data = Data()
)