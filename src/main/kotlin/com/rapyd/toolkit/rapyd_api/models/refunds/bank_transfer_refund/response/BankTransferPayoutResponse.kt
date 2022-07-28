package com.rapyd.toolkit.rapyd_api.models.refunds.bank_transfer_refund.response

data class BankTransferPayoutResponse(
    var status: Status = Status(),
    var `data`: Data = Data()
)