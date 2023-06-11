package com.example.cryptocurrency.presentation.coindetail

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cryptocurrency.common.Resource
import com.example.cryptocurrency.domain.usecases.GetCoinUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class CoinDetailViewModel @Inject constructor(
    private val coinUseCase: GetCoinUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _coinState = mutableStateOf(CoinDetailUIState())
    val coinState: State<CoinDetailUIState> = _coinState

    init {
        savedStateHandle.get<String>("coinId")?.let {
            getCoins(it)
        }
    }

    private fun getCoins(coinId: String) {
        coinUseCase(coinId).onEach { result ->
            when (result) {
                is Resource.Loading -> {
                    _coinState.value = CoinDetailUIState(isLoading = true)
                }
                is Resource.Success -> {
                    _coinState.value = CoinDetailUIState(coinDetail = result.data)
                }
                is Resource.Error -> {
                    _coinState.value =
                        CoinDetailUIState(error = result.message ?: "An unexpected error occurred!")
                }
            }
        }.launchIn(viewModelScope)
    }
}