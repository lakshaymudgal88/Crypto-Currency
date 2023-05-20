package com.example.cryptocurrency

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.cryptocurrency.presentation.Screen
import com.example.cryptocurrency.presentation.coindetail.CoinDetailScreen
import com.example.cryptocurrency.presentation.coinlist.CoinListScreen
import com.example.cryptocurrency.ui.theme.CryptoCurrencyTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CryptoCurrencyTheme {
                CryptoApp()
            }
        }
    }
}

@Composable
fun CryptoApp() {
    Surface(color = MaterialTheme.colors.background) {
        val navController = rememberNavController()
        NavHost(
            navController = navController,
            startDestination = Screen.CoinListScreen.route
        ) {
            composable(route = Screen.CoinListScreen.route) {
                CoinListScreen(navController = navController)
            }
            composable(route = Screen.CoinDetailScreen.route + "/{coinId}") {
                CoinDetailScreen()
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    CryptoCurrencyTheme {
        CryptoApp()
    }
}