package com.rapyd.toolkit.rapyd_api.models.customer.create_customer.request

data class CreateCustomerRequest(
    var business_vat_id: String? = "",
    var email: String? = "",
    var ewallet: String? = "",
    var invoice_prefix: String? = "",
    var metadata: Metadata = Metadata(),
    var name: String? = "",
    var payment_method: PaymentMethod = PaymentMethod(),
    var phone_number: String? = "",
)