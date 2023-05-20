package com.example.cryptocurrency.presentation.coinlist

import com.example.cryptocurrency.domain.model.Coin

data class CoinListUIState(
    val isLoading: Boolean = false,
    val coins: List<Coin> = emptyList(),
    val error: String = ""
)
