// ../ui/screens/PrimeiraTela.kt
package br.pucpr.cryptotrack.ui.screens

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.ListItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import br.pucpr.cryptotrack.data.Moeda

@Composable
fun PrimeiraTela(
    moedas: List<Moeda>
) {
    LazyColumn(
        modifier = Modifier.fillMaxWidth()
    ) {
        items(moedas, key = { it.id }) { moeda ->
            ListItem(
                headlineContent = { Text(moeda.nome) },
                supportingContent = { Text("Valor: ${moeda.valor}") },
            )
            HorizontalDivider(thickness = 0.5.dp)
        }
    }
}