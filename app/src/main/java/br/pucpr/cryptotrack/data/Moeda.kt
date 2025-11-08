// ../app/data/Pessoa.kt
package br.pucpr.cryptotrack.data

data class Moeda(
    val id: Int,
    val nome: String,
    val valor: Float,
    val marketcap: Float,
    var isFavorite: Boolean = false
)