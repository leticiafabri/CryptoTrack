// ../ui/components/AppNavegador.kt
package br.pucpr.cryptotrack.ui.components

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.mutableStateListOf
import br.pucpr.cryptotrack.data.Moeda
import br.pucpr.cryptotrack.ui.screens.PrimeiraTela

@Composable
fun AppNavegador() {
    val moedas = remember {
        mutableStateListOf(
            Moeda(id = 1, nome = "USDT", valor = 6.10f, marketcap = 5.67f),
            Moeda(id = 2, nome = "ETH", valor = 5.30f, marketcap = 3.14f),
            Moeda(id = 3, nome = "BTC", valor = 2.71f, marketcap = 2.1f),
        )
    }
    PrimeiraTela(moedas = moedas)
}