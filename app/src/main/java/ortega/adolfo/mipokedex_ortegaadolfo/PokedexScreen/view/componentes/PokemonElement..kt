package ortega.adolfo.mipokedex_ortegaadolfo.PokedexScreen.view.componentes

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ortega.adolfo.mipokedex_ortegaadolfo.PokedexScreen.model.Domain.Pokemon
import ortega.adolfo.mipokedex_ortegaadolfo.PokedexScreen.model.Domain.data.PokemonRepositorio
import ortega.adolfo.mipokedex_ortegaadolfo.ui.theme.OffWhite
import ortega.adolfo.mipokedex_ortegaadolfo.ui.theme.PokedexTheme

@Composable
fun PokemonRow(pokemon: Pokemon, modifier: Modifier = Modifier) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(10.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Image(
            painter = painterResource(pokemon.imgId),
            contentDescription = "${pokemon.nombre} image",
            modifier = Modifier
                .width(80.dp)
                .padding(10.dp)
        )
        Column(
            modifier = Modifier.fillMaxWidth(0.7f),
            verticalArrangement = Arrangement.spacedBy(2.dp)
        ) {
            Text(
                text = pokemon.nombre,
                style = MaterialTheme.typography.labelLarge
            )
            Text(
                text = pokemon.descripcion,
                fontSize = 10.sp
            )
            Row(
                modifier = Modifier.fillMaxWidth(0.85f),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "${pokemon.Altura} M",
                    style = MaterialTheme.typography.labelMedium
                )
                Text(
                    text = "${pokemon.Peso} KG",
                    style = MaterialTheme.typography.labelMedium
                )
            }
        }
        NumberChip(
            texto = pokemon.numPokedex.removePrefix("#").trimStart('0').ifEmpty { "0" },
            colores = Pair(pokemon.tipo.fondo, pokemon.tipo.texto),
            modifier = Modifier.align(Alignment.Top)
        )
    }
}

@Composable
fun FavoritePokemon(pokemon: Pokemon, modifier: Modifier = Modifier) {
    val colors = Pair(pokemon.tipo.fondo, pokemon.tipo.texto)

    Column(
        modifier = modifier.padding(vertical = 15.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box {
            Box(
                modifier = Modifier
                    .border(
                        border = BorderStroke(
                            width = 5.dp,
                            brush = Brush.sweepGradient(
                                colors = listOf(
                                    colors.first,
                                    OffWhite,
                                    colors.first,
                                    OffWhite,
                                    colors.first
                                )
                            )
                        )
                    )
            ) {
                Image(
                    painter = painterResource(pokemon.imgId),
                    contentDescription = pokemon.nombre,
                    modifier = Modifier
                        .width(75.dp)
                        .padding(5.dp)
                )
            }
            NumberChip(
                texto = pokemon.numPokedex.removePrefix("#").trimStart('0').ifEmpty { "0" },
                colores = colors,
                modifier = Modifier.align(Alignment.BottomEnd)
            )
        }
        Text(
            text = pokemon.nombre,
            style = MaterialTheme.typography.labelLarge
        )
    }
}

@Composable
fun PokemonCell(pokemon: Pokemon, modifier: Modifier = Modifier) {
    val colors = Pair(pokemon.tipo.fondo, pokemon.tipo.texto)

    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        Box {
            Image(
                painter = painterResource(pokemon.imgId),
                contentDescription = pokemon.nombre,
                modifier = Modifier
                    .size(150.dp)
                    .padding(10.dp)
            )
            NumberChip(
                texto = pokemon.numPokedex.removePrefix("#").trimStart('0').ifEmpty { "0" },
                colores = colors,
                modifier = Modifier.align(Alignment.TopEnd)
            )
        }
        Text(
            text = pokemon.nombre,
            style = MaterialTheme.typography.labelLarge,
            fontWeight = FontWeight.Bold
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun pokemonRowPreview() {
    PokedexTheme {
        PokemonRow(pokemon = PokemonRepositorio.pokemones[0])
    }
}

@Preview(showBackground = true)
@Composable
private fun favoritePokemonPreview() {
    PokedexTheme {
        FavoritePokemon(pokemon = PokemonRepositorio.pokemones[3])
    }
}

@Preview(showBackground = true)
@Composable
private fun pokemonCellPreview() {
    PokedexTheme {
        PokemonCell(pokemon = PokemonRepositorio.pokemones[3])
    }
}
