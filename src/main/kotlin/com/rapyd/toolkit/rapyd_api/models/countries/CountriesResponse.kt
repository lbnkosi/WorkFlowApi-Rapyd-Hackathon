package com.rapyd.toolkit.rapyd_api.models.countries

import com.rapyd.toolkit.rapyd_api.models.common.Status

data class CountriesResponse(
    val status: Status = Status(),
    val data: List<DataItem>? = listOf()
)