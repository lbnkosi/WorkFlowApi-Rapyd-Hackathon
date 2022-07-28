package com.rapyd.toolkit.rapyd_api.controllers

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.rapyd.toolkit.rapyd_api.models.customer.fetch_customer.response.GetCustomerResponse
import com.rapyd.toolkit.rapyd_api.models.wallet.company.CreateCompanyWalletRequest
import com.rapyd.toolkit.rapyd_api.models.wallet.company.CreateCompanyWalletResponse
import com.rapyd.toolkit.rapyd_api.models.wallet.get_wallet.GetWalletResponse
import com.rapyd.toolkit.rapyd_api.models.wallet.personal.CreatePersonalWalletRequest
import com.rapyd.toolkit.rapyd_api.models.wallet.personal.CreatePersonalWalletResponse
import com.rapyd.toolkit.rapyd_api.models.wallet.transfer.request.WalletTransferRequest
import com.rapyd.toolkit.rapyd_api.models.wallet.transfer.response.WalletTransferResponse
import com.rapyd.toolkit.util.Constants
import com.rapyd.toolkit.util.HeaderGenerator
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpEntity
import org.springframework.http.HttpMethod
import org.springframework.web.client.RestTemplate
import java.lang.reflect.Type

object WalletController {

    const val PATH = "/v1/user"
    const val WALLET_PATH = "/v1/"
    const val ACCOUNT_TRANSFER_PATH = "/v1/account/transfer"

    fun createPersonalWallet(wallet: CreatePersonalWalletRequest): CreatePersonalWalletResponse {
        val restTemplate = RestTemplate()
        val entity = HttpEntity(Gson().toJson(wallet).replace(" ", ""), HeaderGenerator.generateRapydApiHeaders(Constants.POST_METHOD, PATH, wallet))
        val response = restTemplate.exchange(Constants.BASE_URL + PATH, HttpMethod.POST, entity, String::class.java)
        val type: Type = object : TypeToken<CreatePersonalWalletResponse>() {}.type
        val walletResponse: CreatePersonalWalletResponse = Gson().fromJson(response.body, type)
        return walletResponse
    }

    fun getCustomerWallet(walletId: String?): GetWalletResponse? {
        val restTemplate = RestTemplate()
        val entity = HttpEntity(null, HeaderGenerator.generateRapydApiHeaders(Constants.GET_METHOD, "${PATH}/$walletId", null))
        val response = restTemplate.exchange("${Constants.BASE_URL}${PATH}/$walletId", HttpMethod.GET, entity, String::class.java)
        val type: Type = object : TypeToken<GetWalletResponse>() {}.type
        return Gson().fromJson(response.body, type)
    }

    fun transferFundsBetweenWallet(request: WalletTransferRequest): WalletTransferResponse {
        val restTemplate = RestTemplate()
        val entity = HttpEntity(Gson().toJson(request).replace(" ", ""), HeaderGenerator.generateRapydApiHeaders(Constants.POST_METHOD, ACCOUNT_TRANSFER_PATH, request))
        val response = restTemplate.exchange(Constants.BASE_URL + ACCOUNT_TRANSFER_PATH, HttpMethod.POST, entity, String::class.java)
        val type: Type = object : TypeToken<WalletTransferResponse>() {}.type
        val walletResponse: WalletTransferResponse = Gson().fromJson(response.body, type)
        return walletResponse
    }

    fun deleteWallet(walletId: String?): String {
        val restTemplate = RestTemplate()
        val entity = HttpEntity(null, HeaderGenerator.generateRapydApiHeaders(Constants.DELETE_METHOD, "${PATH}/$walletId", null))
        restTemplate.exchange("${Constants.BASE_URL}${PATH}/$walletId", HttpMethod.DELETE, entity, String::class.java)
        return "Done"
    }

}