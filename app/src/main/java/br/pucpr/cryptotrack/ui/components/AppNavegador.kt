package br.pucpr.cryptotrack.ui.components

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.mutableStateListOf
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import br.pucpr.cryptotrack.data.Moeda
import br.pucpr.cryptotrack.ui.screens.PrimeiraTela
import br.pucpr.cryptotrack.ui.screens.SegundaTela
import br.pucpr.cryptotrack.ui.screens.TerceiraTela

@Composable
fun AppNavegador() {
    val navController = rememberNavController()

    // Lista de moedas (dados simulados)
    val moedas = remember {
        mutableStateListOf(
            Moeda(id = 1, nome = "USDT", valor = 6.10f, marketcap = 5.67f),
            Moeda(id = 2, nome = "ETH", valor = 24379.23f, marketcap = 3.14f),
            Moeda(id = 3, nome = "BTC", valor = 606877.25f, marketcap = 2.1f),
        )
    }

    NavHost(
        navController = navController,
        startDestination = "home"
    ) {
        // Tela inicial
        composable("home") {
            PrimeiraTela(
                moedas = moedas,
                // Quando clicar em uma moeda, navega pra detalhes
                onMoedaClick = { moeda ->
                    navController.navigate("details/${moeda.nome}/${moeda.valor}")
                },
                onFavoritesClick = {
                    navController.navigate("favorites")
                }
            )
        }

        // Tela de detalhes da moeda
        composable("details/{nome}/{valor}") { backStackEntry ->
            val nome = backStackEntry.arguments?.getString("nome") ?: ""
            val valor = backStackEntry.arguments?.getString("valor") ?: ""
            SegundaTela(
                nomeMoeda = nome,
                valor = valor
            )
        }

        // Tela de favoritos
        composable("favorites") {
            TerceiraTela(favoritas = moedas.filter { it.isFavorite })
        }
    }
}
