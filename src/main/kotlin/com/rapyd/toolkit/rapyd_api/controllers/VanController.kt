package com.rapyd.toolkit.rapyd_api.controllers

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.rapyd.toolkit.rapyd_api.models.van.get_bank_account.GetBankAccountResponse
import com.rapyd.toolkit.rapyd_api.models.van.issuebankaccount.request.IssueBankAccountRequest
import com.rapyd.toolkit.rapyd_api.models.van.issuebankaccount.response.IssueBankAccountResponse
import com.rapyd.toolkit.util.Constants
import com.rapyd.toolkit.util.HeaderGenerator
import org.springframework.http.HttpEntity
import org.springframework.http.HttpMethod
import org.springframework.web.client.RestTemplate
import java.lang.reflect.Type

object VanController {

    const val PATH = "/v1/issuing/bankaccounts"

    fun createBankAccount(request: IssueBankAccountRequest): IssueBankAccountResponse {
        val restTemplate = RestTemplate()
        val entity = HttpEntity(Gson().toJson(request).replace(" ", ""), HeaderGenerator.generateRapydApiHeaders(Constants.POST_METHOD, PATH, request))
        val response = restTemplate.exchange(Constants.BASE_URL + PATH, HttpMethod.POST, entity, String::class.java)
        val type: Type = object : TypeToken<IssueBankAccountResponse>() {}.type
        return Gson().fromJson(response.body, type)
    }

    fun getBankAccount(bankAccount: String): GetBankAccountResponse? {
        val restTemplate = RestTemplate()
        val entity = HttpEntity(null, HeaderGenerator.generateRapydApiHeaders(Constants.GET_METHOD, PATH + "/" + bankAccount, null))
        val response = restTemplate.exchange(Constants.BASE_URL + PATH + "/" + bankAccount, HttpMethod.GET, entity, String::class.java)
        val type: Type = object : TypeToken<GetBankAccountResponse>() {}.type
        return Gson().fromJson(response.body, type)
    }

}