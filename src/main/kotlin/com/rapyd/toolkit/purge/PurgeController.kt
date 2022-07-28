package com.rapyd.toolkit.purge

import com.rapyd.toolkit.rapyd_api.controllers.CustomerController
import com.rapyd.toolkit.rapyd_api.controllers.WalletController
import com.rapyd.toolkit.util.Constants
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/purge")
class PurgeController {

    private val DELETE_PATH = "/v1/customers"

    @PostMapping(value = ["/customers"])
    fun deleteAllCustomers(): ResponseEntity<String> {
        CustomerController.getAllCustomers().data.forEach {
            CustomerController.deleteCustomer(it.id)
        }
        return ResponseEntity.ok("Done")
    }

    fun deleteAllOrders() {

    }

    fun deleteWalletContact() {

    }

    @PostMapping(value = ["/wallets"])
    fun deleteWallets() {
        CustomerController.getAllCustomers().data.forEach {
            WalletController.deleteWallet(it.ewallet)
        }
    }

}