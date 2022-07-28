package com.rapyd.toolkit.rapyd_api.models.customer.fetch_customer.response

data class Data(
    val addresses: List<Any> = listOf(),
    val business_vat_id: String = "",
    val created_at: Int = 0,
    val default_payment_method: String = "",
    val delinquent: Boolean = false,
    val description: String = "",
    val discount: String = "",
    val email: String = "",
    val ewallet: String = "",
    val id: String = "",
    val invoice_prefix: String = "",
    val metadata: Metadata = Metadata(),
    val name: String = "",
    val payment_methods: PaymentMethods = PaymentMethods(),
    val phone_number: String = "",
    //val subscriptions: Any
)