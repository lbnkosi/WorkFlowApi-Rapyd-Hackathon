package com.rapyd.toolkit.toolkit.product_toolkit.models.single_product

import com.rapyd.toolkit.rapyd_api.models.products.get_products.single_product_response.GetProductResponse

data class SingleProduct(
    var product: GetProductResponse = GetProductResponse(),
    var addOns: List<GetProductResponse> = listOf()
)
