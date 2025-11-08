// ../app/data/PessoaRepository.kt
package br.pucpr.cryptotrack.data

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class MoedaRepository(private val dao: MoedaDao) {
    val moedas = dao.observarTodas()

    suspend fun criar(nome: String, valor: Float, marketcap: Float, isfavorite: Boolean): Moeda = withContext(Dispatchers.IO) {
        val temp = Moeda(nome = nome, valor = valor, marketcap = marketcap, isFavorite = isfavorite)
        val id = dao.inserir(temp)
        temp.copy(id = id.toInt())
    }

    suspend fun atualizar(p: Moeda) = withContext(Dispatchers.IO) { dao.atualizar(p) }
    suspend fun remover(p: Moeda) = withContext(Dispatchers.IO) { dao.deletar(p) }
}