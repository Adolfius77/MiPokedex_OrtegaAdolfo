package ortega.adolfo.mipokedex_ortegaadolfo.view.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import ortega.adolfo.mipokedex_ortegaadolfo.model.data.PokemonRepositorio
import ortega.adolfo.mipokedex_ortegaadolfo.model.domain.Pokemon
import ortega.adolfo.mipokedex_ortegaadolfo.viewmodel.PokemonDetailViewModel

@Composable
fun PokemonDetailScreen(
    onPokemonNotFound: () -> Unit,
    innerPadding: PaddingValues = PaddingValues(),
    viewModel: PokemonDetailViewModel = viewModel()
) {
    val pokemon = viewModel.pokemon

    if (pokemon == null) {
        LaunchedEffect(Unit) { onPokemonNotFound() }
    } else {
        PokemonDetailContent(
            pokemon = pokemon,
            innerPadding = innerPadding,
            onSaveCompanion = { viewModel.saveCompanion(it) }
        )
    }
}

@Composable
fun PokemonDetailContent(
    pokemon: Pokemon,
    innerPadding: PaddingValues = PaddingValues(),
    onSaveCompanion: (Pokemon) -> Unit = {}
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
        Button(onClick = { onSaveCompanion(pokemon) }) {
            Text("Seleccionar como compañero")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun pokemonmDetailScreen() {
    PokemonDetailContent(pokemon = PokemonRepositorio.pokemones.first())
}
