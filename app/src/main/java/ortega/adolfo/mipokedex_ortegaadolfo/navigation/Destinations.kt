package ortega.adolfo.mipokedex_ortegaadolfo.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import ortega.adolfo.mipokedex_ortegaadolfo.model.domain.Pokemon
import ortega.adolfo.mipokedex_ortegaadolfo.view.screens.LoginScreen
import ortega.adolfo.mipokedex_ortegaadolfo.view.screens.MenuPokedexScreen
import ortega.adolfo.mipokedex_ortegaadolfo.view.screens.PokemonDetailScreen
import ortega.adolfo.mipokedex_ortegaadolfo.view.screens.PokemonHuntScreen
import ortega.adolfo.mipokedex_ortegaadolfo.viewmodel.UserViewModel



fun NavGraphBuilder.loginDestination(
    userViewModel: UserViewModel,
    onLoginSuccess: () -> Unit
) {
    composable<Routes.Login> {
        LoginScreen(viewModel = userViewModel, onLoginSuccess = onLoginSuccess)
    }
}

fun NavGraphBuilder.menuPokedexDestination(
    userViewModel: UserViewModel,
    onPokemonClick: (Pokemon) -> Unit,
    onNavigateToHunt: () -> Unit,
    onLogout: () -> Unit
) {
    composable<Routes.MenuPokedex> {
        MenuPokedexScreen(
            userViewModel = userViewModel,
            onPokemonClick = onPokemonClick,
            onNavigateToHunt = onNavigateToHunt,
            onLogout = onLogout
        )
    }
}

fun NavGraphBuilder.pokemonDetailDestination(onPokemonNotFound: () -> Unit) {
    composable<Routes.PokemonDetail> {
        PokemonDetailScreen(onPokemonNotFound = onPokemonNotFound)
    }
}

fun NavGraphBuilder.pokemonHuntDestination() {
    composable<Routes.PokemonHunt> {
        PokemonHuntScreen()
    }
}
