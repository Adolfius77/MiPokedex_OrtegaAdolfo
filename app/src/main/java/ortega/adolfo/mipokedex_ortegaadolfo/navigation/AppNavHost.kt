package ortega.adolfo.mipokedex_ortegaadolfo.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import ortega.adolfo.mipokedex_ortegaadolfo.viewmodel.UserViewModel

@Composable
fun AppNavHost(
    navController: NavHostController,
    userViewModel: UserViewModel,
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navController,
        startDestination = Routes.Login,
        modifier = modifier
    ) {
        loginDestination(
            userViewModel = userViewModel,
            onLoginSuccess = {
                navController.navigate(Routes.MenuPokedex) {
                    popUpTo<Routes.Login> { inclusive = true }
                    launchSingleTop = true
                }
            }
        )
        menuPokedexDestination(
            userViewModel = userViewModel,
            onPokemonClick = { pokemon ->
                navController.navigate(Routes.PokemonDetail(pokemon.numPokedex))
            },
            onNavigateToHunt = { navController.navigate(Routes.PokemonHunt) },
            onLogout = {
                navController.navigate(Routes.Login) {
                    popUpTo<Routes.MenuPokedex> { inclusive = true }
                    launchSingleTop = true
                }
            }
        )
        pokemonDetailDestination(
            onPokemonNotFound = { navController.popBackStack() }
        )
        pokemonHuntDestination()
    }
}
