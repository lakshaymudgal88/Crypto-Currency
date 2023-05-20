package com.example.cryptocurrency.data.repository

import com.example.cryptocurrency.data.remote.CryptoApi
import com.example.cryptocurrency.data.remote.dto.CoinDetailDto
import com.example.cryptocurrency.data.remote.dto.CoinDto
import com.example.cryptocurrency.domain.repository.CoinRepository
import javax.inject.Inject

class CoinRepositoryImpl @Inject constructor(private val cryptoApi: CryptoApi) : CoinRepository {
    override suspend fun getCoins(): List<CoinDto> {
        return cryptoApi.getCoins()
    }

    override suspend fun getCoinById(coinId: String): CoinDetailDto {
        return cryptoApi.getCoinById(coinId)
    }
}