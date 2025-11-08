// ../ui/screens/QuartaTela.kt
package br.pucpr.cryptotrack.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import br.pucpr.cryptotrack.data.Moeda

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun QuartaTela(
    moeda: Moeda?, // null = criando, não-null = editando
    onSalvar: (nome: String, valor: Float, marketcap: Float) -> Unit,
    onCancelar: () -> Unit
) {
    var nome by rememberSaveable { mutableStateOf(moeda?.nome ?: "") }
    var valorTexto by rememberSaveable { mutableStateOf(moeda?.valor?.toString() ?: "") }
    var marketcapTexto by rememberSaveable { mutableStateOf(moeda?.marketcap?.toString() ?: "") }

    val isValid = nome.isNotBlank() &&
            valorTexto.toFloatOrNull() != null &&
            marketcapTexto.toFloatOrNull() != null

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(if (moeda == null) "Nova Moeda" else "Editar Moeda") },
                navigationIcon = {
                    TextButton(onClick = onCancelar) { Text("Voltar") }
                },
                actions = {
                    TextButton(
                        enabled = isValid,
                        onClick = {
                            val valor = valorTexto.toFloatOrNull() ?: 0f
                            val marketcap = marketcapTexto.toFloatOrNull() ?: 0f
                            onSalvar(nome.trim(), valor, marketcap)
                        }
                    ) { Text("Salvar") }
                }
            )
        }
    ) { inner ->
        Column(
            modifier = Modifier
                .padding(inner)
                .padding(16.dp)
                .fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            OutlinedTextField(
                value = nome,
                onValueChange = { nome = it },
                label = { Text("Nome da moeda") },
                singleLine = true,
                modifier = Modifier.fillMaxWidth()
            )
            OutlinedTextField(
                value = valorTexto,
                onValueChange = { valorTexto = it.filter { ch -> ch.isDigit() || ch == '.' } },
                label = { Text("Valor atual (em R$)") },
                singleLine = true,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                modifier = Modifier.fillMaxWidth()
            )
            OutlinedTextField(
                value = marketcapTexto,
                onValueChange = { marketcapTexto = it.filter { ch -> ch.isDigit() || ch == '.' } },
                label = { Text("Market Cap (em bilhões)") },
                singleLine = true,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                modifier = Modifier.fillMaxWidth()
            )

            if (!isValid) {
                Text(
                    text = "Preencha todos os campos com valores válidos.",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.error
                )
            }
        }
    }
}
