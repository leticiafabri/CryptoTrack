package br.pucpr.cryptotrack.ui.screens

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
fun TerceiraTela(moedas: List<Moeda>, navController: NavController) {
    val favoritas = moedas.filter { it.isFavorite }

    Scaffold(
        bottomBar = {
            BottomNavigationBar(selected = "favorites", onNavigate = { rota -> navController.navigate(rota) })
        }
    ) { padding ->
        if (favoritas.isEmpty()) {
            Box(modifier = Modifier.fillMaxSize().padding(padding), contentAlignment = androidx.compose.ui.Alignment.Center) {
                Text("Nenhuma moeda favorita ainda.")
            }
        } else {
            LazyColumn(modifier = Modifier.padding(padding).padding(16.dp)) {
                items(favoritas.size) { index ->
                    val moeda = favoritas[index]
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 8.dp)
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
}
