package com.rapyd.toolkit.rapyd_api.models.virtual_card.issue_card

data class Data(
    val ewallet_contact: EwalletContact = EwalletContact(),
    val metadata: Metadata = Metadata(),
    val cvv: String = "",
    val card_number: String = "",
    val country_iso_alpha: String = "",
    val bin: String = "",
    val created_at: Int = 0,
    val sub_bin: String = "",
    val card_id: String = "",
    val activated_at: Int = 0,
    val expiration_year: String = "",
    val blocked_reason: String = "",
    val assigned_at: Int = 0,
    val expiration_month: String = "",
    val id: String = "",
    val card_tracking_id: String = "",
    val card_program: String = "",
    val status: String = ""
)