package com.rapyd.toolkit.rapyd_api.controllers

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.rapyd.toolkit.rapyd_api.models.customer.fetch_customer.response.GetCustomerResponse
import com.rapyd.toolkit.rapyd_api.models.products.get_products.response.GetProductsResponse
import com.rapyd.toolkit.rapyd_api.models.products.get_products.single_product_response.GetProductResponse
import com.rapyd.toolkit.util.Constants
import com.rapyd.toolkit.util.HeaderGenerator
import org.springframework.http.HttpEntity
import org.springframework.http.HttpMethod
import org.springframework.web.client.RestTemplate
import java.lang.reflect.Type

object ProductsController {

    const val PRODUCTS_PATH = "/v1/products"

    fun getProduct(productId: String): GetProductResponse {
        val restTemplate = RestTemplate()
        val entity = HttpEntity(null, HeaderGenerator.generateRapydApiHeaders(Constants.GET_METHOD, "${PRODUCTS_PATH}/$productId", null))
        val response = restTemplate.exchange("${Constants.BASE_URL}${PRODUCTS_PATH}/$productId", HttpMethod.GET, entity, String::class.java)
        val type: Type = object : TypeToken<GetProductResponse>() {}.type
        return Gson().fromJson(response.body, type)
    }

    fun getProducts(): GetProductsResponse {
        val restTemplate = RestTemplate()
        val entity = HttpEntity(null, HeaderGenerator.generateRapydApiHeaders(Constants.GET_METHOD, PRODUCTS_PATH, null))
        val response = restTemplate.exchange("${Constants.BASE_URL}${PRODUCTS_PATH}", HttpMethod.GET, entity, String::class.java)
        val type: Type = object : TypeToken<GetProductsResponse>() {}.type
        return Gson().fromJson(response.body, type)
    }

}