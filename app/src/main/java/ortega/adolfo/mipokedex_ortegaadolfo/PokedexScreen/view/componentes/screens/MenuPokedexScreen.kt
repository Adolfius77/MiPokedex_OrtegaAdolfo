package ortega.adolfo.mipokedex_ortegaadolfo.PokedexScreen.view.componentes.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ortega.adolfo.mipokedex_ortegaadolfo.PokedexScreen.ui.FavoritesRow
import ortega.adolfo.mipokedex_ortegaadolfo.PokedexScreen.ui.PokedexGrid
import ortega.adolfo.mipokedex_ortegaadolfo.PokedexScreen.model.Domain.Pokemon
import ortega.adolfo.mipokedex_ortegaadolfo.PokedexScreen.model.Domain.data.PokemonRepositorio
import ortega.adolfo.mipokedex_ortegaadolfo.ui.theme.PokedexTheme

@Composable
fun MenuPokedexScreen(
    innerPadding: PaddingValues = PaddingValues(),
    pokemonList: List<Pokemon> = PokemonRepositorio.pokemones,
    onPokemonClick: (Pokemon) -> Unit = {}
) {
    val favoriteList = pokemonList.filter { it.favorite }.ifEmpty { pokemonList.take(2) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(innerPadding)
            .padding(16.dp)
    ) {
        Text(
            text = "Mis Favoritos",
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 8.dp)
        )
        FavoritesRow(favoriteList = favoriteList, onPokemonClick = onPokemonClick)

        Text(
            text = "Todos mis pokemones",
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(top = 16.dp, bottom = 8.dp)
        )

        PokedexGrid(
            pokemonList = pokemonList,
            modifier = Modifier.weight(1f),
            onPokemonClick = onPokemonClick
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun MenuPokedexScreenPreview() {
    PokedexTheme {
        MenuPokedexScreen()
    }
}
