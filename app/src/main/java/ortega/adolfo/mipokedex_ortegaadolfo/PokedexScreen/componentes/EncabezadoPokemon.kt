package ortega.adolfo.mipokedex_ortegaadolfo.PokedexScreen.componentes

import androidx.compose.foundation.Image
import ortega.adolfo.mipokedex_ortegaadolfo.R

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource


import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ortega.adolfo.mipokedex_ortegaadolfo.pokemones.Pokemon
import ortega.adolfo.mipokedex_ortegaadolfo.ui.theme.BlackBajti

@Composable
fun EncabezadoPokemon(pokemon: Pokemon, modifier: Modifier = Modifier) {
    Box(modifier = modifier.fillMaxSize()) {
        Image(
            painter = painterResource(R.drawable.bg_pokebola),
            contentDescription = null,
            modifier = Modifier.size(300.dp).align(Alignment.BottomEnd).offset(x = 90.dp, y = 60.dp)
        )
        Image(
            painter = painterResource(R.drawable.ic_estrella),
            contentDescription = null,
            modifier = Modifier.align(Alignment.TopEnd).padding(16.dp)
        )
        Column(modifier = Modifier.padding(start = 24.dp, top = 32.dp)) {
            Text(pokemon.nombre, color = Color.White, fontSize = 30.sp, fontWeight = FontWeight.Bold)
            Text(pokemon.numPokedex, color = BlackBajti, fontSize = 20.sp, fontWeight = FontWeight.Bold)
        }
        Image(
            painter = painterResource(pokemon.imgId),
            contentDescription = pokemon.nombre,
            modifier = Modifier.size(width = 160.dp, height = 190.dp).align(Alignment.BottomCenter)
        )
    }
}
