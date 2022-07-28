package com.rapyd.toolkit.rapyd_api.models.orders.get_single_order.response

data class StatusTransitions(
    var canceled: Int = 0,
    var fulfilled: Int = 0,
    var paid: Int = 0,
    var returned: Int = 0,
    var pending: Int = 0,
    var partial: Int = 0
)