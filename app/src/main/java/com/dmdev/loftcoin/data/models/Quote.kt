package com.dmdev.loftcoin.data.models

import com.squareup.moshi.Json

data class Quote(
    @Json(name = "price")
    val price: Double,
    @Json(name = "percent_change_24h")
    val change24h: Double
)