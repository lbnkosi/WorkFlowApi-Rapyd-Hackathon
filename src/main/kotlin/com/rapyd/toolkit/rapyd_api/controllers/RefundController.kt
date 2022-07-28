package com.rapyd.toolkit.rapyd_api.controllers

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.rapyd.toolkit.rapyd_api.models.refunds.bank_transfer_refund.request.BankTransferPayoutRequest
import com.rapyd.toolkit.rapyd_api.models.refunds.bank_transfer_refund.response.BankTransferPayoutResponse
import com.rapyd.toolkit.rapyd_api.models.refunds.cash_payout.request.CashPayoutRequest
import com.rapyd.toolkit.rapyd_api.models.refunds.cash_payout.response.CashPayoutResponse
import com.rapyd.toolkit.rapyd_api.models.refunds.create_beneficiary.request.CreateBeneficiaryRequest
import com.rapyd.toolkit.rapyd_api.models.refunds.create_beneficiary.response.CreateBeneficiaryResponse
import com.rapyd.toolkit.rapyd_api.models.refunds.ewallet_payout.request.EWalletPayoutRequest
import com.rapyd.toolkit.rapyd_api.models.refunds.ewallet_payout.response.EWalletPayoutResponse
import com.rapyd.toolkit.rapyd_api.models.refunds.simulate_payout.SimulatePayoutResponse
import com.rapyd.toolkit.toolkit.refund_toolkit.models.coupon.CouponRequest
import com.rapyd.toolkit.toolkit.refund_toolkit.models.coupon.response.CouponResponse
import com.rapyd.toolkit.toolkit.refund_toolkit.models.refundResponse.CreateRefundResponse
import com.rapyd.toolkit.toolkit.refund_toolkit.models.refund_request.CreatePayoutRequest
import com.rapyd.toolkit.util.Constants
import com.rapyd.toolkit.util.HeaderGenerator
import org.springframework.http.HttpEntity
import org.springframework.http.HttpMethod
import org.springframework.web.client.RestTemplate
import java.lang.reflect.Type

object RefundController {

    private const val CREATE_BENEFICIARY_PATH = "/hosted/disburse/beneficiary"

    private const val PAYOUT_PATH = "/v1/payouts"

    private const val COUPON_PATH = "/v1/coupons"

    fun createBeneficiary(request: CreateBeneficiaryRequest): CreateBeneficiaryResponse {
        val restTemplate = RestTemplate()
        val entity = HttpEntity(Gson().toJson(request).replace(" ", ""), HeaderGenerator.generateRapydApiHeaders(Constants.POST_METHOD, CREATE_BENEFICIARY_PATH, request))
        val response = restTemplate.exchange(Constants.BASE_URL + CREATE_BENEFICIARY_PATH, HttpMethod.POST, entity, String::class.java)
        val type: Type = object : TypeToken<CreateBeneficiaryResponse>() {}.type
        return Gson().fromJson(response.body, type)
    }

    fun createCashPayout(request: CashPayoutRequest): CashPayoutResponse {
        val restTemplate = RestTemplate()
        val entity = HttpEntity(Gson().toJson(request).replace(" ", ""), HeaderGenerator.generateRapydApiHeaders(Constants.POST_METHOD, PAYOUT_PATH, request))
        val response = restTemplate.exchange(Constants.BASE_URL + PAYOUT_PATH, HttpMethod.POST, entity, String::class.java)
        val type: Type = object : TypeToken<SimulatePayoutResponse>() {}.type
        return Gson().fromJson(response.body, type)
    }

    fun createEWalletPayout(request: EWalletPayoutRequest): EWalletPayoutResponse {
        val restTemplate = RestTemplate()
        val entity = HttpEntity(Gson().toJson(request).replace(" ", ""), HeaderGenerator.generateRapydApiHeaders(Constants.POST_METHOD, PAYOUT_PATH, request))
        val response = restTemplate.exchange(Constants.BASE_URL + PAYOUT_PATH, HttpMethod.POST, entity, String::class.java)
        val type: Type = object : TypeToken<EWalletPayoutResponse>() {}.type
        return Gson().fromJson(response.body, type)
    }

    fun createBankTransferPayout(request: CreatePayoutRequest): CreateRefundResponse {
        val restTemplate = RestTemplate()
        val entity = HttpEntity(Gson().toJson(request).replace(" ", ""), HeaderGenerator.generateRapydApiHeaders(Constants.POST_METHOD, PAYOUT_PATH, request))
        val response = restTemplate.exchange(Constants.BASE_URL + PAYOUT_PATH, HttpMethod.POST, entity, String::class.java)
        val type: Type = object : TypeToken<CreateRefundResponse>() {}.type
        return Gson().fromJson(response.body, type)
    }

    fun createCoupon(request: CouponRequest): CouponResponse {
        val restTemplate = RestTemplate()
        val entity = HttpEntity(Gson().toJson(request).replace(" ", ""), HeaderGenerator.generateRapydApiHeaders(Constants.POST_METHOD, COUPON_PATH, request))
        val response = restTemplate.exchange(Constants.BASE_URL + COUPON_PATH, HttpMethod.POST, entity, String::class.java)
        val type: Type = object : TypeToken<CouponResponse>() {}.type
        return Gson().fromJson(response.body, type)
    }

    fun simulatePayout(amount: String, payoutId: String) {
        val restTemplate = RestTemplate()
        val entity = HttpEntity(null, HeaderGenerator.generateRapydApiHeaders(Constants.POST_METHOD, "$PAYOUT_PATH/complete/payout$payoutId/$amount", null))
        val response = restTemplate.exchange(Constants.BASE_URL + PAYOUT_PATH + "/complete/payout" + payoutId + "/" + amount, HttpMethod.POST, entity, String::class.java)
        val type: Type = object : TypeToken<SimulatePayoutResponse>() {}.type
        return Gson().fromJson(response.body, type)
    }

}