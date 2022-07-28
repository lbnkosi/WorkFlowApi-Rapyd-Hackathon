package com.rapyd.toolkit.rapyd_api.models.orders.get_orders.response

data class GetOrdersResponse(
    var status: Status = Status(),
    var data: List<Data> = listOf()
)