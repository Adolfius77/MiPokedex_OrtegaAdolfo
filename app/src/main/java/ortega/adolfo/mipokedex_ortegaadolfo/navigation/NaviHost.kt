package ortega.adolfo.mipokedex_ortegaadolfo.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument

import ortega.adolfo.mipokedex_ortegaadolfo.data.PokemonRepositorio
import ortega.adolfo.mipokedex_ortegaadolfo.screens.MenuPokedexScreen
import ortega.adolfo.mipokedex_ortegaadolfo.screens.PokemonDetailScreen

@Composable
fun MyApp(innerPadding: PaddingValues = PaddingValues()) {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Routes.MenuPokedex.route
    ) {
        composable(Routes.MenuPokedex.route) {
            MenuPokedexScreen(
                innerPadding = innerPadding,
                onPokemonClick = { pokemon ->
                    navController.navigate(Routes.PokemonDetail.createRoute(pokemon.numPokedex))
                }
            )
        }

        composable(
            route = Routes.PokemonDetail.route,
            arguments = listOf(
                navArgument(Routes.PokemonDetail.ARG_NUM_POKEDEX) { type = NavType.StringType }
            )
        ) { backStackEntry ->
            val numPokedex = backStackEntry.arguments
                ?.getString(Routes.PokemonDetail.ARG_NUM_POKEDEX)
            val pokemon = numPokedex?.let { PokemonRepositorio.getPokemonByNumber(it) }

            if (pokemon == null) {
                LaunchedEffect(numPokedex) { navController.popBackStack() }
            } else {
                PokemonDetailScreen(innerPadding = innerPadding, pokemon = pokemon)
            }
        }
    }
}
