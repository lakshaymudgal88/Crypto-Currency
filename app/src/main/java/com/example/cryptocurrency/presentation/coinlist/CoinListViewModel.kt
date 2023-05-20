package com.example.cryptocurrency.presentation.coinlist

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cryptocurrency.common.Resource
import com.example.cryptocurrency.domain.usecases.GetCoinsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class CoinListViewModel @Inject constructor(
    private val coinsUseCase: GetCoinsUseCase
) : ViewModel() {


    private val _coinsState = mutableStateOf(CoinListUIState())
    val coinsState: State<CoinListUIState> = _coinsState

    init {
        getCoins()
    }

    private fun getCoins() {
        coinsUseCase().onEach { result ->
            when (result) {
                is Resource.Loading -> {
                    _coinsState.value = CoinListUIState(isLoading = true)
                }
                is Resource.Success -> {
                    _coinsState.value = CoinListUIState(coins = result.data ?: emptyList())
                }
                is Resource.Error -> {
                    _coinsState.value =
                        CoinListUIState(error = result.message ?: "An unexpected error occurred!")
                }
            }
        }.launchIn(viewModelScope)
    }
}