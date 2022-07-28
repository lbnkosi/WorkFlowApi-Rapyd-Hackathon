package com.rapyd.toolkit.toolkit.product_toolkit.controllers

import com.rapyd.toolkit.toolkit.product_toolkit.models.single_product.SingleProduct
import com.rapyd.toolkit.rapyd_api.controllers.ProductsController
import com.rapyd.toolkit.rapyd_api.models.products.get_products.single_product_response.GetProductResponse
import com.rapyd.toolkit.util.Constants
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping(Constants.PRODUCT_REQUEST_MAPPING)
class ProductController {

    @GetMapping
    fun getProduct(@RequestParam(name = "productId") productId: String?): ResponseEntity<Any>? {
        if (!productId.isNullOrEmpty()) {
            val productResponse = ProductsController.getProduct(productId)
            val addOnList : ArrayList<GetProductResponse> = arrayListOf()
            if (!productResponse.data.metadata.add_ons.isNullOrEmpty()) {
                productResponse.data.metadata.add_ons.forEach {
                    addOnList.add(ProductsController.getProduct(it.add_on_id))
                }
            }
            val singleProduct  = SingleProduct().apply {
                product = productResponse
                addOns = addOnList
            }
            return ResponseEntity.ok(singleProduct)
        }
        return ResponseEntity.ok("No productId specified")
    }

    fun getProduct() {

    }

    fun getProducts() {

    }

}