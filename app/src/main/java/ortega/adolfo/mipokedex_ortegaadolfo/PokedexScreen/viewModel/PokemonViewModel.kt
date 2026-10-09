package ortega.adolfo.mipokedex_ortegaadolfo.PokedexScreen.viewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import ortega.adolfo.mipokedex_ortegaadolfo.Domain.Pokemon
import ortega.adolfo.mipokedex_ortegaadolfo.data.PokemonRepositorio

class PokemonViewModel: ViewModel() {
    var wildPokemon by mutableStateOf<Pokemon?>(null)
        private set

    var capturedPokemon by mutableStateOf<List<Pokemon>>(emptyList())
        private set

    var gonePokemon by mutableStateOf(false)
        private set

    fun searchPokemon(){
        wildPokemon = PokemonRepositorio.pokemones.random()
    }

    fun capturePokemon(){
        wildPokemon?.let {
            val isCaptured = (1..2).random()
            if(isCaptured == 1){
                capturedPokemon = capturedPokemon + it
                gonePokemon = false
            }else{
                gonePokemon = true
            }
            wildPokemon = null
        }
    }
}