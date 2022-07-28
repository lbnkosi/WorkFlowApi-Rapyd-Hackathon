package com.rapyd.toolkit.enums

enum class CountryCodeEnum(val currencies: ArrayList<String>) {
    GB(arrayListOf("GBP")),
    DE(arrayListOf("EUR")),
    DK(arrayListOf("AED", "AUD", "CAD", "CHF", "CZK", "DKK", "HKD", "HRK", "HUF", "ILS", "JPY", "MXN", "NOK", "NZD", "PLN", "RON", "SAR", "SEK", "SGD", "TRY", "USD", "ZAR")),
    MX(arrayListOf("MXN")),
    SG(arrayListOf("SGD", "USD"))
}