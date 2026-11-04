package ortega.adolfo.mipokedex_ortegaadolfo.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import ortega.adolfo.mipokedex_ortegaadolfo.model.data.PokemonRepositorio
import ortega.adolfo.mipokedex_ortegaadolfo.model.data.Users
import ortega.adolfo.mipokedex_ortegaadolfo.model.domain.Pokemon

class MenuPokedexViewModel(private val users: Users) : ViewModel() {

    val pokemonList: List<Pokemon> = PokemonRepositorio.pokemones

    val favoriteList: List<Pokemon> =
        pokemonList.filter { it.favorite }.ifEmpty { pokemonList.take(2) }

    var isGrid by mutableStateOf(users.isGridDisplay())
        private set

    fun onDisplayChange(grid: Boolean) {
        isGrid = grid
        users.saveDisplayPreference(grid)
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = checkNotNull(this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY])
                MenuPokedexViewModel(Users(application))
            }
        }
    }
}
