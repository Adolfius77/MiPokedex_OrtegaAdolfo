package ortega.adolfo.mipokedex_ortegaadolfo.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import ortega.adolfo.mipokedex_ortegaadolfo.model.data.interfaces.IPokemonRepository
import ortega.adolfo.mipokedex_ortegaadolfo.model.domain.Pokemon
import ortega.adolfo.mipokedex_ortegaadolfo.model.sqlLite.DatabaseHelper
import ortega.adolfo.mipokedex_ortegaadolfo.model.sqlLite.PokemonDAO
import ortega.adolfo.mipokedex_ortegaadolfo.model.sqlLite.PokemonSQLRepository

class AddPokemonViewModel(
    private val pokemonRepository: IPokemonRepository
) : ViewModel() {

    private val _pokemonList = MutableStateFlow<List<Pokemon>>(emptyList())
    val pokemonList: StateFlow<List<Pokemon>> = _pokemonList.asStateFlow()

    var isPokemonSaved: Boolean by mutableStateOf(false)
        private set

    init {
        loadPokemons()
    }

    fun loadPokemons() {
        viewModelScope.launch {
            _pokemonList.value = pokemonRepository.getAllPokemons()
        }
    }

    fun savePokemon(pokemon: Pokemon) {
        viewModelScope.launch {
            try {
                pokemonRepository.insertPokemon(pokemon)
                isPokemonSaved = true
                loadPokemons()
            } catch (e: Exception) {
                isPokemonSaved = false
            }
        }
    }

    fun resetSavedState() {
        isPokemonSaved = false
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = checkNotNull(this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY])
                val dbHelper = DatabaseHelper(application)
                val pokemonDAO = PokemonDAO(dbHelper)
                val pokemonRepository = PokemonSQLRepository(pokemonDAO)
                AddPokemonViewModel(pokemonRepository)
            }
        }
    }
}
