// ../app/data/Pessoa.kt
package br.pucpr.cryptotrack.data

data class Moeda(
    val id: Long = 0L,
    val nome: String,
    val valor: Float,
    val marketcap: Float
)