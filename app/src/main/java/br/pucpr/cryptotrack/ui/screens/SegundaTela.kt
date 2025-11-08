package br.pucpr.cryptotrack.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import br.pucpr.cryptotrack.data.Moeda
import br.pucpr.cryptotrack.ui.components.BottomNavigationBar

@Composable
fun SegundaTela(moeda: Moeda, navController: NavController) {
    Scaffold(
        bottomBar = {
            BottomNavigationBar(selected = "details", onNavigate = { rota -> navController.navigate(rota) })
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .padding(16.dp)
                .fillMaxSize(),
        ) {
            Text(text = moeda.nome, style = MaterialTheme.typography.headlineMedium)
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = "Valor: R$ ${moeda.valor}")
            Text(text = "Market Cap: ${moeda.marketcap}")
        }
    }
}
