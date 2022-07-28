package com.rapyd.toolkit.rapyd_api.models.products.create_product.response

data class Data(
    var id: String = "",
    var active: Boolean = false,
    var attributes: List<String> = listOf(),
    var created_at: Int = 0,
    var description: String = "",
    var images: List<String> = listOf(),
    var metadata: Metadata = Metadata(),
    var name: String = "",
    var package_dimensions: PackageDimensions = PackageDimensions(),
    var shippable: Boolean = false,
    var skus: List<Any> = listOf(),
    var statement_descriptor: String = "",
    var type: String = "",
    var unit_label: String = "",
    var updated_at: Int = 0
)