package br.pucpr.cryptotrack.ui.components

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.mutableStateListOf
import androidx.navigation.compose.rememberNavController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import br.pucpr.cryptotrack.data.Moeda
import br.pucpr.cryptotrack.ui.screens.PrimeiraTela
import br.pucpr.cryptotrack.ui.screens.SegundaTela
import br.pucpr.cryptotrack.ui.screens.TerceiraTela

@Composable
fun AppNavegador() {
    val navController = rememberNavController()

    val moedas = remember {
        mutableStateListOf(
            Moeda(id = 1, nome = "USDT", valor = 6.10f, marketcap = 5.67f),
            Moeda(id = 2, nome = "ETH", valor = 5.30f, marketcap = 3.14f),
            Moeda(id = 3, nome = "BTC", valor = 2.71f, marketcap = 2.10f),
        )
    }

    NavHost(navController = navController, startDestination = "home") {

        composable("home") {
            PrimeiraTela(moedas = moedas, navController = navController)
        }

        composable(
            route = "details/{moedaId}",
            arguments = listOf(navArgument("moedaId") { type = NavType.IntType })
        ) { backStackEntry ->
            val moedaId = backStackEntry.arguments?.getInt("moedaId") ?: return@composable
            val moeda = moedas.firstOrNull { it.id == moedaId }
            moeda?.let {
                SegundaTela(moeda = it, navController = navController)
            }
        }

        composable("favorites") {
            TerceiraTela(moedas = moedas, navController = navController)
        }
    }
}
