package com.rapyd.toolkit.rapyd_api.controllers

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.rapyd.toolkit.rapyd_api.models.virtual_card.issue_card.IssueVirtualCardRequest
import com.rapyd.toolkit.rapyd_api.models.virtual_card.issue_card.IssueVirtualCardResponse
import com.rapyd.toolkit.util.Constants
import com.rapyd.toolkit.util.Constants.BASE_URL
import com.rapyd.toolkit.util.Constants.POST_METHOD
import com.rapyd.toolkit.util.HeaderGenerator
import com.rapyd.toolkit.util.HeaderGenerator.generateRapydApiHeaders
import org.springframework.http.HttpEntity
import org.springframework.http.HttpMethod
import org.springframework.http.HttpMethod.POST
import org.springframework.web.client.RestTemplate
import java.lang.reflect.Type

object IssuingController {

    const val VIRTUAL_CARD_PATH = "/v1/issuing/cards"

    fun createVirtualCard(request: IssueVirtualCardRequest): IssueVirtualCardResponse {
        val restTemplate = RestTemplate()
        val entity = HttpEntity(Gson().toJson(request).replace(" ", ""), generateRapydApiHeaders(POST_METHOD, VIRTUAL_CARD_PATH, request))
        val response = restTemplate.exchange(BASE_URL + VIRTUAL_CARD_PATH, POST, entity, String::class.java)
        val type: Type = object : TypeToken<IssueVirtualCardResponse>() {}.type
        return Gson().fromJson(response.body, type)
    }

}