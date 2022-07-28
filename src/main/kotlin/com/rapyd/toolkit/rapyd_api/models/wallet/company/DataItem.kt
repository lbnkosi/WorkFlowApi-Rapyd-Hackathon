package com.rapyd.toolkit.rapyd_api.models.wallet.company

import com.rapyd.toolkit.rapyd_api.models.common.Address

data class DataItem(
    val country: String = "",
    val metadata: Metadata,
    val gender: String = "",
    val identification_number: String = "",
    val date_of_birth: String = "",
    val identification_type: String = "",
    val created_at: Int = 0,
    val verification_status: String = "",
    val contact_type: String = "",
    val compliance_profile: Int = 0,
    val issued_card_data: IssuedCardData,
    val id: String = "",
    val house_type: String = "",
    val first_name: String = "",
    val email: String = "",
    val second_last_name: String = "",
    val address: Address,
    val send_notifications: Boolean = false,
    val last_name: String = "",
    val middle_name: String = "",
    val ewallet: String = "",
    val marital_status: String = "",
    val business_details: BusinessDetails,
    val mothers_name: String = "",
    val nationality: String = "",
    val phone_number: String = ""
)