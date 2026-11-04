package ortega.adolfo.mipokedex_ortegaadolfo.view.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import ortega.adolfo.mipokedex_ortegaadolfo.R
import ortega.adolfo.mipokedex_ortegaadolfo.model.data.PokemonRepositorio
import ortega.adolfo.mipokedex_ortegaadolfo.model.domain.Pokemon
import ortega.adolfo.mipokedex_ortegaadolfo.ui.theme.Green
import ortega.adolfo.mipokedex_ortegaadolfo.ui.theme.LigtGreen
import ortega.adolfo.mipokedex_ortegaadolfo.ui.theme.PokedexTheme
import ortega.adolfo.mipokedex_ortegaadolfo.ui.theme.blue
import ortega.adolfo.mipokedex_ortegaadolfo.view.componentes.FavoritesRow
import ortega.adolfo.mipokedex_ortegaadolfo.view.componentes.MenuPokedex
import ortega.adolfo.mipokedex_ortegaadolfo.view.componentes.PokedexGrid
import ortega.adolfo.mipokedex_ortegaadolfo.viewmodel.MenuPokedexViewModel
import ortega.adolfo.mipokedex_ortegaadolfo.viewmodel.UserViewModel

@Composable
fun MenuPokedexScreen(
    userViewModel: UserViewModel,
    onPokemonClick: (Pokemon) -> Unit,
    onNavigateToHunt: () -> Unit,
    onLogout: () -> Unit,
    innerPadding: PaddingValues = PaddingValues(),
    menuViewModel: MenuPokedexViewModel = viewModel(factory = MenuPokedexViewModel.Factory)
) {
    MenuPokedexContent(
        pokemonList = menuViewModel.pokemonList,
        favoriteList = menuViewModel.favoriteList,
        isGrid = menuViewModel.isGrid,
        onDisplayChange = menuViewModel::onDisplayChange,
        onPokemonClick = onPokemonClick,
        onNavigateToHunt = onNavigateToHunt,
        onLogout = {
            userViewModel.logout()
            onLogout()
        },
        innerPadding = innerPadding
    )
}

@Composable
fun MenuPokedexContent(
    pokemonList: List<Pokemon>,
    favoriteList: List<Pokemon>,
    isGrid: Boolean,
    onDisplayChange: (Boolean) -> Unit,
    onPokemonClick: (Pokemon) -> Unit,
    onNavigateToHunt: () -> Unit,
    onLogout: () -> Unit,
    innerPadding: PaddingValues = PaddingValues()
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(innerPadding)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Mis Favoritos",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold
                )
                OutlinedButton(onClick = onLogout) {
                    Text(text = "Cerrar sesión")
                }
            }

            FavoritesRow(favoriteList = favoriteList, onPokemonClick = onPokemonClick)

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp, bottom = 8.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Todos mis pokemones",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold
                )
                Switch(
                    checked = isGrid,
                    onCheckedChange = onDisplayChange,
                    colors = SwitchDefaults.colors(
                        checkedThumbColor = Green,
                        checkedTrackColor = LigtGreen,
                        uncheckedThumbColor = blue,
                        uncheckedBorderColor = Color.Transparent
                    ),
                    thumbContent = {
                        Icon(
                            painter = painterResource(
                                if (isGrid) R.drawable.ic_bolitas else R.drawable.ic_article
                            ),
                            contentDescription = if (isGrid) "grid icon" else "list icon",
                            modifier = Modifier.size(SwitchDefaults.IconSize)
                        )
                    }
                )
            }

            if (isGrid) {
                PokedexGrid(
                    pokemonList = pokemonList,
                    modifier = Modifier.weight(1f),
                    onPokemonClick = onPokemonClick
                )
            } else {
                MenuPokedex(
                    pokemonList = pokemonList,
                    modifier = Modifier.weight(1f),
                    onPokemonClick = onPokemonClick
                )
            }
        }

        ExtendedFloatingActionButton(
            onClick = onNavigateToHunt,
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(16.dp)
        ) {
            Text(text = "Atrapar nuevo")
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun MenuPokedexScreenPreview() {
    PokedexTheme {
        MenuPokedexContent(
            pokemonList = PokemonRepositorio.pokemones,
            favoriteList = PokemonRepositorio.getFavoritePokemon().take(2),
            isGrid = false,
            onDisplayChange = {},
            onPokemonClick = {},
            onNavigateToHunt = {},
            onLogout = {}
        )
    }
}
