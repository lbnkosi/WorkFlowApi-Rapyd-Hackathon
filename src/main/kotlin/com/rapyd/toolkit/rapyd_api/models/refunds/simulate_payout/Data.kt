package com.rapyd.toolkit.rapyd_api.models.refunds.simulate_payout

data class Data(
    var id: String = "",
    var payout_type: String = "",
    var payout_method_type: String = "",
    var amount: Double = 0.0,
    var payout_currency: String = "",
    var sender_amount: Int = 0,
    var sender_currency: String = "",
    var status: String = "",
    var sender_country: String = "",
    var sender: Sender = Sender(),
    var beneficiary_country: String = "",
    var beneficiary: Beneficiary = Beneficiary(),
    var fx_rate: Double = 0.0,
    var instructions: Instructions = Instructions(),
    var instructions_value: InstructionsValue = InstructionsValue(),
    var ewallets: List<Ewallet> = listOf(),
    var metadata: Metadata = Metadata(),
    var description: String = "",
    var created_at: Int = 0,
    var payout_fees: Any = Any(),
    var expiration: Any = Any(),
    var merchant_reference_id: Any = Any(),
    var paid_at: String = "",
    var identifier_type: Any = Any(),
    var identifier_value: Any = Any(),
    var error: Any = Any(),
    var paid_amount: Int = 0,
    var statement_descriptor: Any = Any()
)