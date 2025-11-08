// ../app/data/Pessoa.kt
package br.pucpr.cryptotrack.data
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "moedas")
data class Moeda(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val nome: String,
    val valor: Float,
    val marketcap: Float,
    var isFavorite: Boolean = false
)