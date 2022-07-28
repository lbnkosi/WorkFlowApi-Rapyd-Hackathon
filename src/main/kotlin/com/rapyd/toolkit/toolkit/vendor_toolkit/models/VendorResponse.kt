package com.rapyd.toolkit.toolkit.vendor_toolkit.models

data class VendorResponse(
    var walletBalance: Int = 0,
    var totalOrders: Int = 0,
    var totalCustomers: Int = 0,

    var totalCompletedOrders: Int = 0,
    var totalIncompleteOrders: Int = 0,

    var totalRefunds: Int = 0,
    var totalRefundsAmount: Int = 0,

    var totalPendingPayments: Int = 0,
    var totalPaymentsMade: Int = 0,

    var totalDepositsFromCustomersSoFar: Int = 0,
    var totalDepositsAmountSoFar: Int = 0,
    var totalAcrossAllWallets: Int = 0
)