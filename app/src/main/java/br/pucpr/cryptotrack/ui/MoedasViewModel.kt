
package br.pucpr.cryptotrack.ui

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import br.pucpr.cryptotrack.data.AppDatabase
import br.pucpr.cryptotrack.data.Moeda
import br.pucpr.cryptotrack.data.MoedaRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class MoedasViewModel(private val repo: MoedaRepository) : ViewModel() {

    // UI state reativo (lista)
    val moedas: StateFlow<List<Moeda>> =
        repo.moedas.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = emptyList()
        )

    fun salvar(editandoId: Int?, nome: String, valor: Float, marketcap: Float, isFavorite: Boolean = false) {
        viewModelScope.launch {
            if (editandoId == null) {
                repo.criar(nome, valor, marketcap, isFavorite)
            } else {
                val atual = moedas.value.firstOrNull { it.id == editandoId } ?: return@launch
                repo.atualizar(atual.copy(nome = nome, valor = valor, marketcap = marketcap, isFavorite = isFavorite))
            }
        }
    }

    fun excluir(p: Moeda) {
        viewModelScope.launch(Dispatchers.IO) { repo.remover(p) }
    }

    // Factory simples (sem Hilt) para injetar DAO/Repo
    companion object {
        fun factory(context: Context) = object : ViewModelProvider.Factory {
            @Suppress("UNCHECKED_CAST")
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                val dao = AppDatabase.get(context).moedaDao()
                val repo = MoedaRepository(dao)
                return MoedasViewModel(repo) as T
            }
        }
    }
}