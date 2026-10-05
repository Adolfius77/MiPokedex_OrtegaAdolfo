package ortega.adolfo.mipokedex_ortegaadolfo.PokedexScreen.ui

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import ortega.adolfo.mipokedex_ortegaadolfo.PokedexScreen.PokemonRow
import ortega.adolfo.mipokedex_ortegaadolfo.pokemones.Pokemon
import ortega.adolfo.mipokedex_ortegaadolfo.pokemones.PokemonRepositorio

@Composable
fun MenuPokedex(pokemonList: List<Pokemon>, innerPadding: PaddingValues) {
    LazyColumn(contentPadding = innerPadding) {
        items(pokemonList) { pokemon ->
            PokemonRow(pokemon)
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun menuPokedexPreview() {
    MenuPokedex(pokemonList = PokemonRepositorio.pokemones, innerPadding = PaddingValues())
}
