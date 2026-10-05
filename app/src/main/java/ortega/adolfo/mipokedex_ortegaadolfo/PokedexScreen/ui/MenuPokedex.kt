package ortega.adolfo.mipokedex_ortegaadolfo.PokedexScreen.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ortega.adolfo.mipokedex_ortegaadolfo.PokedexScreen.componentes.FavoritePokemon
import ortega.adolfo.mipokedex_ortegaadolfo.PokedexScreen.componentes.PokemonCell
import ortega.adolfo.mipokedex_ortegaadolfo.PokedexScreen.componentes.PokemonRow
import ortega.adolfo.mipokedex_ortegaadolfo.pokemones.Pokemon
import ortega.adolfo.mipokedex_ortegaadolfo.pokemones.PokemonRepositorio
import ortega.adolfo.mipokedex_ortegaadolfo.ui.theme.PokedexTheme

@Composable
fun MenuPokedex(pokemonList: List<Pokemon>, modifier: Modifier = Modifier) {
    LazyColumn(modifier = modifier) {
        items(pokemonList) { pokemon ->
            PokemonRow(pokemon)
        }
    }
}

@Composable
fun FavoritesRow(favoriteList: List<Pokemon>, modifier: Modifier = Modifier) {
    LazyRow(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        items(favoriteList) { pokemon ->
            FavoritePokemon(pokemon = pokemon)
        }
    }
}

@Composable
fun PokedexGrid(pokemonList: List<Pokemon>, modifier: Modifier = Modifier) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(3),
        contentPadding = PaddingValues(horizontal = 5.dp, vertical = 20.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp),
        horizontalArrangement = Arrangement.spacedBy(5.dp),
        modifier = modifier
    ) {
        items(pokemonList) { pokemon ->
            PokemonCell(pokemon = pokemon)
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun menuPokedexPreview() {
    PokedexTheme {
        MenuPokedex(pokemonList = PokemonRepositorio.pokemones)
    }
}
