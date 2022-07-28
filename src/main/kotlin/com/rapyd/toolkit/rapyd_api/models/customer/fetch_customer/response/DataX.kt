package com.rapyd.toolkit.rapyd_api.models.customer.fetch_customer.response

data class DataX(
    val acs_check: String,
    val bin_details: BinDetails,
    val category: String,
    val cvv_check: String,
    val expiration_month: String,
    val expiration_year: String,
    val fingerprint_token: String,
    val id: String,
    val image: String,
    val last4: String,
    val metadata: Any,
    val name: Any,
    val next_action: String,
    val supporting_documentation: String,
    val type: String,
    val webhook_url: String
)