package br.pucpr.cryptotrack.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import br.pucpr.cryptotrack.data.Moeda
import br.pucpr.cryptotrack.ui.components.BottomNavigationBar

@Composable
fun PrimeiraTela(moedas: List<Moeda>, navController: NavController) {
    Scaffold(
        bottomBar = {
            BottomNavigationBar(
                selected = "home",
                onNavigate = { rota -> navController.navigate(rota) }
            )
        }
    ) { padding ->
        LazyColumn(modifier = Modifier.padding(padding).padding(16.dp)) {
            items(moedas.size) { index ->
                val moeda = moedas[index]
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp)
                        .clickable {
                            navController.navigate("details/${moeda.id}")
                        }
                ) {
                    Column(Modifier.padding(16.dp)) {
                        Text(text = moeda.nome, style = MaterialTheme.typography.titleLarge)
                        Text(text = "Valor: R$ ${moeda.valor}")
                        Text(text = "Market Cap: ${moeda.marketcap}")
                    }
                }
            }
        }
    }
}
