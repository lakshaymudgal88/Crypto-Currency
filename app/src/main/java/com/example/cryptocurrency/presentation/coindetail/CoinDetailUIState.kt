package com.example.cryptocurrency.presentation.coindetail

import com.example.cryptocurrency.domain.model.CoinDetail

data class CoinDetailUIState(
    val isLoading: Boolean = false,
    val coinDetail: CoinDetail? = null,
    val error: String = ""
)
