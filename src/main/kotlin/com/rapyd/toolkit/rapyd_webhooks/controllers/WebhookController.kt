package com.rapyd.toolkit.rapyd_webhooks.controllers

import com.google.gson.Gson
import com.rapyd.toolkit.rapyd_webhooks.models.transfers.simulate_transfer.SimulateTransferResponse
import com.rapyd.toolkit.toolkit.order_toolkit.controllers.OrderController
import com.rapyd.toolkit.util.Constants
import com.rapyd.toolkit.util.EmailServiceImpl
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping(Constants.WEBHOOK_MAPPING)
class WebhookController {

    @Autowired
    val emailServiceImpl : EmailServiceImpl? = null

    @PostMapping(value = ["/banktransfer/notification"])
    fun notifyBankTransfer(@RequestBody body: SimulateTransferResponse) {
        OrderController.allocateFundsToOrder(body)
        emailServiceImpl?.sendSimpleMessage("rapydhack@gmail.com", "Webhook", Gson().toJson(body))
    }

    @PostMapping(value = ["/beneficiary/created/notification"])
    fun notifyBeneficiaryCreated(@RequestBody body: SimulateTransferResponse) {
        emailServiceImpl?.sendSimpleMessage("rapydhack@gmail.com", "Webhook", Gson().toJson(body))
    }

    @PostMapping(value = ["/any"])
    fun notifyBankTransfer(@RequestBody body: Any) {
        emailServiceImpl?.sendSimpleMessage("rapydhack@gmail.com", "Webhook", Gson().toJson(body))
    }

    @PostMapping(value = ["/trello/notification"])
    fun trelloNotification(@RequestBody body: Any) {
        emailServiceImpl?.sendSimpleMessage("rapydhack@gmail.com", "Webhook", Gson().toJson(body))
    }

}