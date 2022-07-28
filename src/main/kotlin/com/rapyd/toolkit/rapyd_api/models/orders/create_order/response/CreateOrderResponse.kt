package com.rapyd.toolkit.rapyd_api.models.orders.create_order.response

data class CreateOrderResponse(
    var status: Status = Status(),
    var `data`: Data = Data()
)