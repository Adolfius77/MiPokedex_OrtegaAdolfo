package ortega.adolfo.mipokedex_ortegaadolfo.navigation

import android.net.Uri
import kotlinx.serialization.Serializable

sealed class Routes(val route: String) {

    @Serializable
    object MenuPokedex : Routes("menu_pokedex")


    object PokemonDetail : Routes("pokemon_detail/{numPokedex}") {
        const val ARG_NUM_POKEDEX = "numPokedex"

        fun createRoute(numPokedex: String) = "pokemon_detail/${Uri.encode(numPokedex)}"
    }

    object PokemonHunt : Routes("pokemon_hunt")
}
