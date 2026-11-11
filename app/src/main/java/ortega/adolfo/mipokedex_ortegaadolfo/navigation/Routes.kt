package ortega.adolfo.mipokedex_ortegaadolfo.navigation

import kotlinx.serialization.Serializable

sealed interface Routes {

    @Serializable
    data object Login : Routes

    @Serializable
    data object MenuPokedex : Routes

    @Serializable
    data class PokemonDetail(val numPokedex: String) : Routes

    @Serializable
    data object PokemonHunt : Routes
    @Serializable
    data object Profile : Routes

}
