package ortega.adolfo.mipokedex_ortegaadolfo.view.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import ortega.adolfo.mipokedex_ortegaadolfo.R
import ortega.adolfo.mipokedex_ortegaadolfo.model.domain.Pokemon
import ortega.adolfo.mipokedex_ortegaadolfo.utilities.TipoPokemon
import ortega.adolfo.mipokedex_ortegaadolfo.viewmodel.PokemonDetailViewModel

@Composable
fun PokemonDetailScreen(
    onPokemonNotFound: () -> Unit,
    innerPadding: PaddingValues = PaddingValues(),
    viewModel: PokemonDetailViewModel = viewModel(factory = PokemonDetailViewModel.Factory)
) {
    val pokemon = viewModel.pokemon
    val isCompanion by viewModel.isCompanion.collectAsStateWithLifecycle()
    val snackbarHostState = remember { SnackbarHostState() }

    LaunchedEffect(viewModel.showSelectedMessage) {
        if (viewModel.showSelectedMessage) {
            snackbarHostState.showSnackbar("Compañero seleccionado")
            viewModel.messageShown()
        }
    }

    if (pokemon == null) {
        LaunchedEffect(Unit) { onPokemonNotFound() }
    } else {
        PokemonDetailContent(
            pokemon = pokemon,
            isCompanion = isCompanion,
            snackbarHostState = snackbarHostState,
            innerPadding = innerPadding,
            onSelectCompanion = viewModel::selectCompanion
        )
    }
}

@Composable
fun PokemonDetailContent(
    pokemon: Pokemon,
    isCompanion: Boolean,
    snackbarHostState: SnackbarHostState = remember { SnackbarHostState() },
    innerPadding: PaddingValues = PaddingValues(),
    onSelectCompanion: () -> Unit = {}
) {
    Scaffold(
        snackbarHost = { SnackbarHost(snackbarHostState) },
        contentWindowInsets = WindowInsets(0, 0, 0, 0)
    ) { scaffoldPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .padding(scaffoldPadding)
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
            if (isCompanion) {
                Text(
                    text = "⭐ Es tu compañero",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(top = 8.dp)
                )
            } else {
                Button(onClick = onSelectCompanion) {
                    Text("Seleccionar como compañero")
                }
            }
        }
    }
}

private val previewPokemon = Pokemon(
    nombre = "Pikachu",
    Altura = 0.4,
    Habilidad = "Electricidad Estática",
    Peso = 6,
    descripcion = "Pokémon eléctrico de ejemplo",
    numPokedex = "#0025",
    tipo = TipoPokemon.ELECTRICO,
    imgId = R.drawable.pikachu,
    favorite = true
)

@Preview(showBackground = true)
@Composable
fun PokemonDetailNoCompanionPreview() {
    PokemonDetailContent(pokemon = previewPokemon, isCompanion = false)
}

@Preview(showBackground = true)
@Composable
fun PokemonDetailCompanionPreview() {
    PokemonDetailContent(pokemon = previewPokemon, isCompanion = true)
}