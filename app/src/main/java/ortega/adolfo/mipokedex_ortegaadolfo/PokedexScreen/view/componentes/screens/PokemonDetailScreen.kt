package ortega.adolfo.mipokedex_ortegaadolfo.PokedexScreen.view.componentes.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import ortega.adolfo.mipokedex_ortegaadolfo.PokedexScreen.model.Domain.Pokemon
import ortega.adolfo.mipokedex_ortegaadolfo.PokedexScreen.model.Domain.data.PokemonRepositorio

@Composable
fun PokemonDetailScreen(
    innerPadding: PaddingValues = PaddingValues(),
    pokemon: Pokemon = PokemonRepositorio.pokemones.first()
) {
    Column(modifier = Modifier.padding(innerPadding)) {
        Text(text = pokemon.nombre)
        Image(painterResource(pokemon.imgId), contentDescription =  "${pokemon.nombre} image")

    }
}
