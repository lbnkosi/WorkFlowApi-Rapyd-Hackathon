package com.rapyd.toolkit.rapyd_api.controllers

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.rapyd.toolkit.rapyd_api.models.orders.create_order.request.CreateOrderRequest
import com.rapyd.toolkit.rapyd_api.models.orders.create_order.response.CreateOrderResponse
import com.rapyd.toolkit.rapyd_api.models.orders.create_order.response.Metadata
import com.rapyd.toolkit.rapyd_api.models.orders.get_orders.response.GetOrdersResponse
import com.rapyd.toolkit.rapyd_api.models.orders.get_single_order.response.GetSingleOrderResponse
import com.rapyd.toolkit.rapyd_api.models.orders.update_order.response.UpdateOrderResponse
import com.rapyd.toolkit.toolkit.wallet_toolkit.models.update_wallet.UpdateOrderBugMetaData
import com.rapyd.toolkit.toolkit.wallet_toolkit.models.update_wallet.UpdateOrderMetaData
import com.rapyd.toolkit.util.Constants
import com.rapyd.toolkit.util.HeaderGenerator
import org.springframework.http.HttpEntity
import org.springframework.http.HttpMethod
import org.springframework.web.client.RestTemplate
import java.lang.reflect.Type

object OrderController {

    private const val ORDER_PATH = "/v1/orders"

    fun createOrder(request: CreateOrderRequest): CreateOrderResponse {
        val restTemplate = RestTemplate()
        val entity = HttpEntity(Gson().toJson(request).replace(" ", ""), HeaderGenerator.generateRapydApiHeaders(Constants.POST_METHOD, ORDER_PATH, request))
        val response = restTemplate.exchange(Constants.BASE_URL + ORDER_PATH, HttpMethod.POST, entity, String::class.java)
        val type: Type = object : TypeToken<CreateOrderResponse>() {}.type
        return Gson().fromJson(response.body, type)
    }

    fun getOrderList():GetOrdersResponse {
        val restTemplate = RestTemplate()
        val entity = HttpEntity(null, HeaderGenerator.generateRapydApiHeaders(Constants.GET_METHOD, ORDER_PATH, null))
        val response = restTemplate.exchange(Constants.BASE_URL + ORDER_PATH, HttpMethod.GET, entity, String::class.java)
        val type: Type = object : TypeToken<GetOrdersResponse>() {}.type
        return Gson().fromJson(response.body, type)
    }

    fun getOrder(orderId: String): GetSingleOrderResponse {
        val restTemplate = RestTemplate()
        val entity = HttpEntity(null, HeaderGenerator.generateRapydApiHeaders(Constants.GET_METHOD, ORDER_PATH + "/" + orderId, null))
        val response = restTemplate.exchange(Constants.BASE_URL + ORDER_PATH + "/" + orderId, HttpMethod.GET, entity, String::class.java)
        val type: Type = object : TypeToken<GetSingleOrderResponse>() {}.type
        return Gson().fromJson(response.body, type)
    }

    fun updateOrder(orderId: String, body: UpdateOrderMetaData): UpdateOrderResponse {
        val restTemplate = RestTemplate()
        val entity = HttpEntity(Gson().toJson(body).replace(" ", ""), HeaderGenerator.generateRapydApiHeaders(Constants.POST_METHOD, ORDER_PATH + "/" + orderId, body))
        val response = restTemplate.exchange(Constants.BASE_URL + ORDER_PATH + "/" + orderId, HttpMethod.POST, entity, String::class.java)
        val type: Type = object : TypeToken<UpdateOrderResponse>() {}.type
        return Gson().fromJson(response.body, type)
    }

    fun updateOrderBugFix(orderId: String, body: UpdateOrderBugMetaData): UpdateOrderResponse {
        val restTemplate = RestTemplate()
        val entity = HttpEntity(Gson().toJson(body).replace(" ", ""), HeaderGenerator.generateRapydApiHeaders(Constants.POST_METHOD, ORDER_PATH + "/" + orderId, body))
        val response = restTemplate.exchange(Constants.BASE_URL + ORDER_PATH + "/" + orderId, HttpMethod.POST, entity, String::class.java)
        val type: Type = object : TypeToken<UpdateOrderResponse>() {}.type
        return Gson().fromJson(response.body, type)
    }

    fun deleteAllOrders() {

    }

}