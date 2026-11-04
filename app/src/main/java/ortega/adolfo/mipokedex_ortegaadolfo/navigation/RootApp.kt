package ortega.adolfo.mipokedex_ortegaadolfo.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import ortega.adolfo.mipokedex_ortegaadolfo.ui.theme.PokedexTheme
import ortega.adolfo.mipokedex_ortegaadolfo.viewmodel.UserViewModel

@Composable
fun RootApp() {
    PokedexTheme {
        val navController = rememberNavController()

        val userViewModel: UserViewModel = viewModel(factory = UserViewModel.Factory)

        Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
            AppNavHost(
                navController = navController,
                userViewModel = userViewModel,
                modifier = Modifier.padding(innerPadding)
            )
        }
    }
}
