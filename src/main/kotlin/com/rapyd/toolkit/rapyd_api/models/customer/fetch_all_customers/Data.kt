package com.rapyd.toolkit.rapyd_api.models.customer.fetch_all_customers

data class Data(
    val addresses: List<Any>,
    val business_vat_id: String?,
    val created_at: Int,
    val default_payment_method: String?,
    val delinquent: Boolean,
    val description: String?,
    val discount: Any,
    val email: String?,
    val ewallet: String?,
    val id: String?,
    val invoice_prefix: String?,
    val metadata: Metadata?,
    val name: String?,
    val payment_methods: PaymentMethods? = PaymentMethods(),
    val phone_number: String?,
    val subscriptions: Any
)