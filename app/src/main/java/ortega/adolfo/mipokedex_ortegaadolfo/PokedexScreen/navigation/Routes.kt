package ortega.adolfo.mipokedex_ortegaadolfo.PokedexScreen.navigation

import android.net.Uri

sealed class Routes(val route: String) {

    object MenuPokedex : Routes("menu_pokedex")

    /**
     * Ruta con parametro. [route] es solo la PLANTILLA que registra el NavHost
     * ("pokemon_detail/{numPokedex}"); para navegar se usa [createRoute], que
     * rellena el hueco con el valor real.
     */
    object PokemonDetail : Routes("pokemon_detail/{numPokedex}") {
        const val ARG_NUM_POKEDEX = "numPokedex"

        // Uri.encode porque numPokedex viene como "#0025" y '#' rompe la ruta.
        fun createRoute(numPokedex: String) = "pokemon_detail/${Uri.encode(numPokedex)}"
    }

    object PokemonHunt : Routes("pokemon_hunt")
}
