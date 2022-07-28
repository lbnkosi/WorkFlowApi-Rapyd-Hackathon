package com.rapyd.toolkit.rapyd_api.models.customer.fetch_all_customers

data class IssuedBankAccount(
    val bank: String,
    val country_iso: String,
    val currency: String,
    val id: String,
    val linked_ewallet: String
)