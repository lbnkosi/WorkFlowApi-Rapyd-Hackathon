package com.rapyd.toolkit.rapyd_api.models.orders.get_single_order.response

data class GetSingleOrderResponse(
    var status: Status = Status(),
    var `data`: Data = Data()
)