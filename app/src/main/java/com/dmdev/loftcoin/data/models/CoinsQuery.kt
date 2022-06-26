package com.dmdev.loftcoin.data.models

data class CoinsQuery(
    val currency: String,
    val forceUpdate: Boolean = true,
    val sortBy: SortBy = SortBy.RANK
)