package com.example.cryptocurrency.presentation

sealed class Screen(val route: String) {
    object CoinListScreen : Screen("screen_list_screen")
    object CoinDetailScreen : Screen("screen_detail_screen")
}
