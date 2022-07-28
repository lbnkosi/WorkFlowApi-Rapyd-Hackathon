package com.rapyd.toolkit.rapyd_api.models.wallet.company

data class Data(
    val metadata: Metadata,
    val last_name: String = "",
    val verification_status: String = "",
    val type: String = "",
    val ewallet_reference_id: String = "",
    val phone_number: String = "",
    val id: String = "",
    val category: String = "",
    val first_name: String = "",
    val email: String = "",
    val contacts: Contacts,
    val status: String = ""
)