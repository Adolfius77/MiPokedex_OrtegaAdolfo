package ortega.adolfo.mipokedex_ortegaadolfo.screens

import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ortega.adolfo.mipokedex_ortegaadolfo.ui.FavoritesRow
import ortega.adolfo.mipokedex_ortegaadolfo.ui.PokedexGrid
import ortega.adolfo.mipokedex_ortegaadolfo.Domain.Pokemon
import ortega.adolfo.mipokedex_ortegaadolfo.R
import ortega.adolfo.mipokedex_ortegaadolfo.data.PokemonRepositorio
import ortega.adolfo.mipokedex_ortegaadolfo.ui.MenuPokedex
import ortega.adolfo.mipokedex_ortegaadolfo.ui.theme.Green
import ortega.adolfo.mipokedex_ortegaadolfo.ui.theme.LigtGreen
import ortega.adolfo.mipokedex_ortegaadolfo.ui.theme.PokedexTheme
import ortega.adolfo.mipokedex_ortegaadolfo.ui.theme.blue

@Composable
fun MenuPokedexScreen(
    innerPadding: PaddingValues = PaddingValues(),
    pokemonList: List<Pokemon> = PokemonRepositorio.pokemones,
    onPokemonClick: (Pokemon) -> Unit = {}
) {
    val favoriteList = pokemonList.filter { it.favorite }.ifEmpty { pokemonList.take(2) }
    var grid by remember { mutableStateOf(false) }


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
        Switch(checked = grid,
            onCheckedChange = {
                grid = it
            },
            colors = SwitchDefaults.colors(
                checkedThumbColor = Green,
                checkedTrackColor = LigtGreen,
                uncheckedThumbColor = blue,
                uncheckedBorderColor = Color.Transparent


            ),
            thumbContent = if (grid){
                {
                    Icon(painterResource(R.drawable.ic_bolitas),
                        contentDescription = "grid icon",
                        modifier = Modifier.size(SwitchDefaults.IconSize))
                }
            } else {
                {
                    Icon(painterResource(R.drawable.ic_article),
                        contentDescription = "list icon",
                        modifier = Modifier.size(SwitchDefaults.IconSize))
                }
            }
        )


        FavoritesRow(favoriteList = favoriteList, onPokemonClick = onPokemonClick)

        Text(
            text = "Todos mis pokemones",
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(top = 16.dp, bottom = 8.dp)
        )
        if(grid){
            PokedexGrid(
                pokemonList = pokemonList,
                modifier = Modifier.weight(1f),
                onPokemonClick = onPokemonClick
            )
        }else{
            MenuPokedex(
                pokemonList = pokemonList,
                modifier = Modifier.weight(1f)
            )
        }

    }
}

@Preview(showBackground = true)
@Composable
private fun MenuPokedexScreenPreview() {
    PokedexTheme {
        MenuPokedexScreen()
    }
}
