package com.rapyd.toolkit.rapyd_api.controllers

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.rapyd.toolkit.rapyd_api.models.customer.create_customer.request.CreateCustomerRequest
import com.rapyd.toolkit.rapyd_api.models.customer.create_customer.response.CreateCustomerResponse
import com.rapyd.toolkit.rapyd_api.models.customer.fetch_all_customers.GetAllCustomersResponse
import com.rapyd.toolkit.rapyd_api.models.customer.fetch_customer.response.GetCustomerResponse
import com.rapyd.toolkit.rapyd_api.models.virtual_card.issue_card.IssueVirtualCardRequest
import com.rapyd.toolkit.rapyd_api.models.virtual_card.issue_card.IssueVirtualCardResponse
import com.rapyd.toolkit.util.Constants
import com.rapyd.toolkit.util.Constants.BASE_URL
import com.rapyd.toolkit.util.Constants.DELETE_METHOD
import com.rapyd.toolkit.util.Constants.GET_METHOD
import com.rapyd.toolkit.util.Constants.POST_METHOD
import com.rapyd.toolkit.util.HeaderGenerator
import com.rapyd.toolkit.util.HeaderGenerator.generateRapydApiHeaders
import org.springframework.http.HttpEntity
import org.springframework.http.HttpMethod
import org.springframework.http.HttpMethod.*
import org.springframework.web.client.RestTemplate
import java.lang.reflect.Type

object CustomerController {

    private const val CUSTOMERS_CARD_PATH = "/v1/customers"

    fun createCustomer(request: CreateCustomerRequest): CreateCustomerResponse {
        val restTemplate = RestTemplate()
        val entity = HttpEntity(Gson().toJson(request).replace(" ", ""), generateRapydApiHeaders(POST_METHOD, CUSTOMERS_CARD_PATH, request))
        val response = restTemplate.exchange(BASE_URL + CUSTOMERS_CARD_PATH, POST, entity, String::class.java)
        val type: Type = object : TypeToken<CreateCustomerResponse>() {}.type
        return Gson().fromJson(response.body, type)
    }

    fun getCustomer(customerID: String?): GetCustomerResponse {
        val restTemplate = RestTemplate()
        val entity = HttpEntity(null, generateRapydApiHeaders(GET_METHOD, "$CUSTOMERS_CARD_PATH/$customerID", null))
        val response = restTemplate.exchange("$BASE_URL$CUSTOMERS_CARD_PATH/$customerID", GET, entity, String::class.java)
        val type: Type = object : TypeToken<GetCustomerResponse>() {}.type
        return Gson().fromJson(response.body, type)
    }

    fun getAllCustomers():GetAllCustomersResponse {
        val restTemplate = RestTemplate()
        val entity = HttpEntity(null, generateRapydApiHeaders(GET_METHOD, "$CUSTOMERS_CARD_PATH?limit=100", null))
        val response = restTemplate.exchange("$BASE_URL$CUSTOMERS_CARD_PATH?limit=100", GET, entity, String::class.java)
        val type: Type = object : TypeToken<GetAllCustomersResponse>() {}.type
        return Gson().fromJson(response.body, type)
    }

    fun updateCustomer(request: Any, customerID: String?): GetCustomerResponse {
        val restTemplate = RestTemplate()
        val entity = HttpEntity(Gson().toJson(request).replace(" ", ""), generateRapydApiHeaders(POST_METHOD, "$CUSTOMERS_CARD_PATH/$customerID", request))
        val response = restTemplate.exchange("$BASE_URL$CUSTOMERS_CARD_PATH/$customerID", POST, entity, String::class.java)
        val type: Type = object : TypeToken<GetCustomerResponse>() {}.type
        return Gson().fromJson(response.body, type)
    }

    fun deleteCustomer(customerID: String?): String {
        val restTemplate = RestTemplate()
        val entity = HttpEntity(null, generateRapydApiHeaders(DELETE_METHOD, "$CUSTOMERS_CARD_PATH/$customerID", null))
        restTemplate.exchange("$BASE_URL$CUSTOMERS_CARD_PATH/$customerID", DELETE, entity, String::class.java)
        return "Done"
    }

}