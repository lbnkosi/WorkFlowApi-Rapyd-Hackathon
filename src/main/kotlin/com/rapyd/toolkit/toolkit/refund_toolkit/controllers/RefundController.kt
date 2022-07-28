package com.rapyd.toolkit.toolkit.refund_toolkit.controllers

import com.rapyd.toolkit.enums.RefundTypeEnum
import com.rapyd.toolkit.rapyd_api.controllers.CustomerController
import com.rapyd.toolkit.rapyd_api.controllers.OrderController
import com.rapyd.toolkit.rapyd_api.controllers.RefundController
import com.rapyd.toolkit.rapyd_api.models.customer.create_customer.request.Beneficiaries
import com.rapyd.toolkit.rapyd_api.models.refunds.bank_transfer_refund.request.BankTransferPayoutRequest
import com.rapyd.toolkit.sheets.SheetsController
import com.rapyd.toolkit.sheets.models.Refunds
import com.rapyd.toolkit.toolkit.payment_toolkit.models.van.UpdateCustomerMetaData
import com.rapyd.toolkit.toolkit.refund_toolkit.models.coupon.CouponRequest
import com.rapyd.toolkit.toolkit.refund_toolkit.models.general.CreateBeneficiaryRequest
import com.rapyd.toolkit.toolkit.refund_toolkit.models.general.Refund
import com.rapyd.toolkit.toolkit.refund_toolkit.models.general.RefundRequest
import com.rapyd.toolkit.toolkit.refund_toolkit.models.general.UpdateCustomerMetaDataRequest
import com.rapyd.toolkit.toolkit.refund_toolkit.models.refund_request.CreatePayoutRequest
import com.rapyd.toolkit.toolkit.wallet_toolkit.controllers.WalletController
import com.rapyd.toolkit.toolkit.wallet_toolkit.models.update_wallet.UpdateOrderMetaData
import com.rapyd.toolkit.toolkit.wallet_toolkit.models.wallet_transfer.request.WalletTransferRequest
import com.rapyd.toolkit.util.Constants
import com.rapyd.toolkit.util.EmailServiceImpl
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import com.rapyd.toolkit.rapyd_api.models.refunds.create_beneficiary.request.CreateBeneficiaryRequest as RapydCreateBeneficiaryRequest

@RestController
@RequestMapping(Constants.REFUND_REQUEST_MAPPING)
class RefundController {

    @Autowired
    val emailServiceImpl: EmailServiceImpl? = null

    @PostMapping(value = ["/simulatePayout"])
    fun simulatePayout(@RequestParam(value = "payoutId") payoutId: String, @RequestParam(value = "amount") amount: String) {
        RefundController.simulatePayout(amount, payoutId)
    }

    @PostMapping(value = ["/beneficiary"])
    fun createBeneficiary(@RequestBody request: CreateBeneficiaryRequest): ResponseEntity<Any> {
        val createBeneficiaryRequest = RapydCreateBeneficiaryRequest()
        val beneficiaryResponse = RefundController.createBeneficiary(createBeneficiaryRequest)
        val customer = CustomerController.getCustomer(request.customerId)
        //customer.data.metadata.beneficiary.add(Beneficiaries(beneficiary_id = beneficiaryResponse.data.id, beneficiary_name = customer.data.name))
        CustomerController.updateCustomer(UpdateCustomerMetaDataRequest(metadata = customer.data.metadata), customerID = customer.data.id)
        emailServiceImpl?.sendSimpleMessage(customer.data.email, "Beneficiary Link", beneficiaryResponse.data.redirect_url)
        return ResponseEntity.ok(beneficiaryResponse)
    }

    @PostMapping(value = ["/refund"])
    fun refund(@RequestBody request: RefundRequest): ResponseEntity<Any> {
        request.refundItems.forEach {
            when (it.type) {
                RefundTypeEnum.BANK_TRANSFER_PAYOUT_REFUND.toString() -> refundUsingBankTransfer(request, it)
                RefundTypeEnum.WALLET_TRANSFER_REFUND.toString()-> refundToWallet(request, it)
                RefundTypeEnum.CARD_PAYOUT_REFUND.toString() -> refundToCard(request, it)
                RefundTypeEnum.COUPON_REFUND.toString() -> couponRefund(request, it)
            }
        }
        val order = OrderController.getOrder(request.orderId)
        order.data.metadata.main_payment.product_name = "REFUND"
        OrderController.updateOrder(order.data.id, UpdateOrderMetaData(metadata = order.data.metadata))
        return ResponseEntity.ok(SheetsController.getAllRefunds())
    }

    fun refundUsingBankTransfer(request: RefundRequest, refund: Refund) {
        val order = OrderController.getOrder(request.orderId)
        val customer = CustomerController.getCustomer(request.customerId)

        val refundResponse = RefundController.createBankTransferPayout(CreatePayoutRequest(payout_amount = refund.amount.toString(), ewallet = "ewallet_d19c17400fbbf7980a9b7a898c0f582b"))

        order.data.metadata.is_refunded = true
        customer.data.metadata.refunds?.add(Refunds().apply {
            refundId = refundResponse.data.id
            refundAmount = refund.amount
            customerId = customer.data.id
            email = customer.data.email
            refundMethod = "BANK_TRANSFER"
        })

        CustomerController.updateCustomer(UpdateCustomerMetaData().apply { metadata = customer.data.metadata } , customer.data.id)
        OrderController.updateOrder(order.data.id, UpdateOrderMetaData(metadata = order.data.metadata))
    }

    fun refundToCard(request: RefundRequest, refund: Refund) {
        val order = OrderController.getOrder(request.orderId)
        val customer = CustomerController.getCustomer(request.customerId)
        val walletTransferRequest = WalletTransferRequest()
        walletTransferRequest.order_id = request.orderId
        walletTransferRequest.is_for_order = true
        walletTransferRequest.destination_ewallet = refund.walletId
        walletTransferRequest.source_ewallet = "ewallet_d19c17400fbbf7980a9b7a898c0f582b"
        walletTransferRequest.currency = order.data.currency
        walletTransferRequest.amount = refund.amount
        WalletController.walletTransfer(walletTransferRequest)
        order.data.metadata.is_refunded = true
        customer.data.metadata.refunds?.add(Refunds().apply {
            refundId = refund.walletId
            refundAmount = refund.amount
            customerId = customer.data.id
            email = customer.data.email
            refundMethod = "CARD_REFUND"
        })
        CustomerController.updateCustomer(UpdateCustomerMetaData().apply { metadata = customer.data.metadata }, customer.data.id)
        OrderController.updateOrder(order.data.id, UpdateOrderMetaData(metadata = order.data.metadata))
    }

    fun refundToWallet(request: RefundRequest, refund: Refund) {
        val order = OrderController.getOrder(request.orderId)
        val customer = CustomerController.getCustomer(request.customerId)
        val walletTransferRequest = WalletTransferRequest()
        walletTransferRequest.order_id = request.orderId
        walletTransferRequest.is_for_order = true
        walletTransferRequest.destination_ewallet = refund.walletId
        walletTransferRequest.source_ewallet = "ewallet_d19c17400fbbf7980a9b7a898c0f582b"
        walletTransferRequest.currency = order.data.currency
        walletTransferRequest.amount = refund.amount
        WalletController.walletTransfer(walletTransferRequest)
        order.data.metadata.is_refunded = true
        customer.data.metadata.refunds?.add(Refunds().apply {
            refundId = refund.walletId
            refundAmount = refund.amount
            customerId = customer.data.id
            email = customer.data.email
            refundMethod = "WALLET_TRANSFER"
        })
        CustomerController.updateCustomer(UpdateCustomerMetaData().apply { metadata = customer.data.metadata }, request.customerId)
        OrderController.updateOrder(order.data.id, UpdateOrderMetaData(metadata = order.data.metadata))
    }

    fun couponRefund(request: RefundRequest, refund: Refund) {
        val order = OrderController.getOrder(request.orderId)
        val customer = CustomerController.getCustomer(request.customerId)
        val refundResponse = RefundController.createCoupon(CouponRequest().apply {
            this.amount_off = refund.amount
            this.currency = "USD"
            this.duration = "repeating"
        })
        order.data.metadata.is_refunded = true
        customer.data.metadata.refunds?.add(Refunds().apply {
            refundId = refundResponse.data.id
            refundAmount = refund.amount
            customerId = customer.data.id
            email = customer.data.email
            refundMethod = "BANK_TRANSFER"
        })
        CustomerController.updateCustomer(UpdateCustomerMetaData().apply { metadata = customer.data.metadata }, customer.data.id)
        OrderController.updateOrder(order.data.id, UpdateOrderMetaData(metadata = order.data.metadata))
    }

}