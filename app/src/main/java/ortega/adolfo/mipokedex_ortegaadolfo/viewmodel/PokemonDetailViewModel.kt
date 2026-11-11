package ortega.adolfo.mipokedex_ortegaadolfo.viewmodel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.createSavedStateHandle
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import androidx.navigation.toRoute
import kotlinx.coroutines.launch
import ortega.adolfo.mipokedex_ortegaadolfo.model.data.CompanionDataStore
import ortega.adolfo.mipokedex_ortegaadolfo.model.data.PokemonRepositorio
import ortega.adolfo.mipokedex_ortegaadolfo.model.domain.Pokemon
import ortega.adolfo.mipokedex_ortegaadolfo.navigation.Routes

class PokemonDetailViewModel(
    savedStateHandle: SavedStateHandle,
    private val companionDataStore: CompanionDataStore
) : ViewModel() {

    private val numPokedex: String = savedStateHandle.toRoute<Routes.PokemonDetail>().numPokedex

    val pokemon: Pokemon? = PokemonRepositorio.getPokemonByNumber(numPokedex)

    fun saveCompanion(pokemon: Pokemon) {
        viewModelScope.launch {
            companionDataStore.saveCompanion(pokemon)
        }
    }
}
