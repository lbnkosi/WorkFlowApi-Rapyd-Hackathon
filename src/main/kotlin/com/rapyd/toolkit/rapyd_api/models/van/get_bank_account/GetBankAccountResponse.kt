package com.rapyd.toolkit.rapyd_api.models.van.get_bank_account

data class GetBankAccountResponse(
    var status: Status = Status(),
    var `data`: Data = Data()
)