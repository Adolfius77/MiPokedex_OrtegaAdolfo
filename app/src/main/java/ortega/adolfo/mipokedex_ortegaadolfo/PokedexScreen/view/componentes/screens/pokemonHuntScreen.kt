package ortega.adolfo.mipokedex_ortegaadolfo.PokedexScreen.view.componentes.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import ortega.adolfo.mipokedex_ortegaadolfo.PokedexScreen.viewModel.PokemonViewModel
import ortega.adolfo.mipokedex_ortegaadolfo.ui.theme.PokedexTheme

@Composable
fun PokemonHuntScreen(
    innerPadding: PaddingValues = PaddingValues(),
    viewModel: PokemonViewModel = viewModel()
) {
    val wild = viewModel.wildPokemon

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(innerPadding)
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        if (wild == null) {
            if (viewModel.gonePokemon) {
                Text(text = "El pokemon se escapo...")
            }
            Button(onClick = { viewModel.searchPokemon() }) {
                Text(text = "Buscar pokemon en la hierva")
            }
        } else {
            Text(text = wild.nombre, style = MaterialTheme.typography.titleLarge)
            Image(
                painter = painterResource(wild.imgId),
                contentDescription = wild.nombre,
                modifier = Modifier.size(150.dp)
            )
            Button(onClick = { viewModel.capturePokemon() }) {
                Text(text = "Capturar a ${wild.nombre}")
            }
        }

        Text(text = "Capturados: ${viewModel.capturedPokemon.size}")
    }
}

@Preview(showBackground = true)
@Composable
private fun previewPokemonHuntScreen() {
    PokedexTheme {
        PokemonHuntScreen()
    }
}
