package com.rapyd.toolkit.rapyd_api.models.orders.update_order.response

data class UpdateOrderResponse(
    var status: Status = Status(),
    var `data`: Data = Data()
)