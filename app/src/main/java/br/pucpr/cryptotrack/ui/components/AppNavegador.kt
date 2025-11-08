package br.pucpr.cryptotrack.ui.components

import android.R.attr.type
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.*
import androidx.navigation.navArgument
import br.pucpr.cryptotrack.data.Moeda
import br.pucpr.cryptotrack.ui.MoedasViewModel
import br.pucpr.cryptotrack.ui.screens.PrimeiraTela
import br.pucpr.cryptotrack.ui.screens.SegundaTela
import br.pucpr.cryptotrack.ui.screens.TerceiraTela
import br.pucpr.cryptotrack.ui.screens.QuartaTela

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppNavegador() {
    val navController = rememberNavController()
    val context = LocalContext.current
    val vm: MoedasViewModel = viewModel(factory = MoedasViewModel.factory(context))

    val moedas by vm.moedas.collectAsStateWithLifecycle()

    Scaffold { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = "home",
            modifier = Modifier.padding(innerPadding)
        ) {
            composable("home") {
                PrimeiraTela(
                    moedas = moedas,
                    navController = navController
                )
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
                TerceiraTela(
                    moedas = moedas.filter { it.isFavorite },
                    navController = navController
                )
            }

            composable("cadastro") {
                QuartaTela(
                    moeda = null,
                    onSalvar = { nome, valor, marketcap ->
                        vm.salvar(null, nome, valor, marketcap)
                        navController.popBackStack()
                    },
                    onCancelar = { navController.popBackStack() }
                )
            }
        }
    }
}
