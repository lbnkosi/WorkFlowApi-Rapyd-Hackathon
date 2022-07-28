package com.rapyd.toolkit.sheets.models

import com.rapyd.toolkit.rapyd_api.models.orders.get_orders.response.ListedPayments

data class OrderResponse(
    var orderId: String = "",
    var amount: String = "",
    var email: String = "",
    var customerId: String = "",
    var currency: String = "",
    var itemName: String? = "",
    var allocateFundsToOrder: Boolean = true,
    var isOrderComplete: Boolean = false,
    var status: String = "",
    var refunded: Boolean = false,
    var paymentList: ArrayList<ListedPayments> = arrayListOf()
)