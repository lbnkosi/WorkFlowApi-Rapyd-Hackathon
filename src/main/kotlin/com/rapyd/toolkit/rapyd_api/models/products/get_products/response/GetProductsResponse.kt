package com.rapyd.toolkit.rapyd_api.models.products.get_products.response

data class GetProductsResponse(
    var status: Status = Status(),
    var data: List<Data> = listOf()
)