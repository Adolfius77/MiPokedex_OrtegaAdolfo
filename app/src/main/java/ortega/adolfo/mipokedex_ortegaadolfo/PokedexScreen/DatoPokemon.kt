package ortega.adolfo.mipokedex_ortegaadolfo.PokedexScreen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ortega.adolfo.mipokedex_ortegaadolfo.ui.theme.BlackBajti
import ortega.adolfo.mipokedex_ortegaadolfo.ui.theme.PokedexTheme
import ortega.adolfo.mipokedex_ortegaadolfo.ui.theme.Rojo

@Composable
fun DatoPokemon(etiqueta: String, valor: String, modifier: Modifier = Modifier) {
    Row(modifier = modifier, horizontalArrangement = Arrangement.spacedBy(8.dp)) {
        Text(etiqueta, color = Rojo, fontSize = 22.sp, fontWeight = FontWeight.Bold)
        Text(valor, color = BlackBajti, fontSize = 22.sp)
    }
}

@Preview(showBackground = true)
@Composable
private fun DatoPokemonPreview() {
    PokedexTheme {
        DatoPokemon(etiqueta = "Altura:", valor = "0.7 m")
    }
}