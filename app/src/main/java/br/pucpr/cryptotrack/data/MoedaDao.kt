// ../app/data/PessoaDao.kt
package br.pucpr.cryptotrack.data

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface MoedaDao {
    @Query("SELECT * FROM moedas ORDER BY id DESC")
    fun observarTodas(): Flow<List<Moeda>>

    @Insert
    suspend fun inserir(moeda: Moeda): Long

    @Update
    suspend fun atualizar(moeda: Moeda)

    @Delete
    suspend fun deletar(moeda: Moeda)
}