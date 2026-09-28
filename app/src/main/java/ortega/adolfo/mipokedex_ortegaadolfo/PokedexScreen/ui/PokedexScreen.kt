package ortega.adolfo.mipokedex_ortegaadolfo.PokedexScreen.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import ortega.adolfo.mipokedex_ortegaadolfo.PokedexScreen.EncabezadoPokemon
import ortega.adolfo.mipokedex_ortegaadolfo.PokedexScreen.NavegacionPokemon
import ortega.adolfo.mipokedex_ortegaadolfo.PokedexScreen.TarjetaInfo
import ortega.adolfo.mipokedex_ortegaadolfo.pokemones.Pokemon
import ortega.adolfo.mipokedex_ortegaadolfo.pokemones.PokemonRepositorio
import ortega.adolfo.mipokedex_ortegaadolfo.ui.theme.Amarillo
import ortega.adolfo.mipokedex_ortegaadolfo.ui.theme.PokedexTheme

@Composable
fun PokedexScreen(pokemones: List<Pokemon> = PokemonRepositorio.pokemones) {
    var indice by rememberSaveable { mutableIntStateOf(0) }
    val total = pokemones.size

    PokedexContenido(
        actual = pokemones[indice],
        anterior = pokemones[(indice - 1 + total) % total],
        siguiente = pokemones[(indice + 1) % total],
        onAnterior = { indice = (indice - 1 + total) % total },
        onSiguiente = { indice = (indice + 1) % total }
    )
}

@Composable
fun PokedexContenido(
    actual: Pokemon, anterior: Pokemon, siguiente: Pokemon,
    onAnterior: () -> Unit, onSiguiente: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(modifier.fillMaxSize().background(Amarillo).statusBarsPadding()) {
        EncabezadoPokemon(actual, Modifier.weight(0.35f))
        TarjetaInfo(actual, Modifier.weight(0.65f)) {
            NavegacionPokemon(anterior, siguiente, onAnterior, onSiguiente)
        }
    }
}

@Preview(showSystemUi = true)
@Composable
private fun PokedexScreenPreview() { PokedexTheme { PokedexScreen() } }