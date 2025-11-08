// ../app/data/MoedaDao.kt
package br.pucpr.cryptotrack.data

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface MoedaDao {

    // reatividade sem coroutines na UI
    @Query("SELECT * FROM moedas ORDER BY id DESC")
    fun observarTodas(): LiveData<List<Moeda>>

    // funções síncronas (apenas para POC)
    @Insert
    fun inserir(moeda: Moeda): Long

    @Update
    fun atualizar(moeda: Moeda)

    @Delete
    fun deletar(moeda: Moeda)
}