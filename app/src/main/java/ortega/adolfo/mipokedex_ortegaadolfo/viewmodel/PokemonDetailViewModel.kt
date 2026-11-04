package ortega.adolfo.mipokedex_ortegaadolfo.viewmodel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.navigation.toRoute
import ortega.adolfo.mipokedex_ortegaadolfo.model.data.PokemonRepositorio
import ortega.adolfo.mipokedex_ortegaadolfo.model.domain.Pokemon
import ortega.adolfo.mipokedex_ortegaadolfo.navigation.Routes

class PokemonDetailViewModel(savedStateHandle: SavedStateHandle) : ViewModel() {

    private val numPokedex: String = savedStateHandle.toRoute<Routes.PokemonDetail>().numPokedex

    val pokemon: Pokemon? = PokemonRepositorio.getPokemonByNumber(numPokedex)
}
