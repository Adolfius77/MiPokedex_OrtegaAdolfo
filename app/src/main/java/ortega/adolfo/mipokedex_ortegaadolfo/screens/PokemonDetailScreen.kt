package ortega.adolfo.mipokedex_ortegaadolfo.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import ortega.adolfo.mipokedex_ortegaadolfo.Domain.Pokemon
import ortega.adolfo.mipokedex_ortegaadolfo.data.PokemonRepositorio

@Composable
fun PokemonDetailScreen(
    innerPadding: PaddingValues = PaddingValues(),
    pokemon: Pokemon = PokemonRepositorio.pokemones.first()
) {
    Column(
        modifier = Modifier
            .padding(innerPadding)
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = pokemon.nombre,
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth()
        )
        Image(
            painter = painterResource(pokemon.imgId),
            contentDescription = "${pokemon.nombre} image"
        )
    }
}

@Preview(showBackground = true)
@Composable
fun pokemonmDetailScreen() {
    PokemonDetailScreen()
}
