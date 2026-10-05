package ortega.adolfo.mipokedex_ortegaadolfo.PokedexScreen.componentes

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ortega.adolfo.mipokedex_ortegaadolfo.R
import ortega.adolfo.mipokedex_ortegaadolfo.pokemones.Pokemon

private val FormaTarjeta = RoundedCornerShape(topStart = 32.dp, topEnd = 32.dp)

@Composable
fun TarjetaInfo(
    pokemon: Pokemon,
    modifier: Modifier = Modifier,
    navegacion: @Composable () -> Unit = {}
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(Color.White, FormaTarjeta)
            .navigationBarsPadding()
            .padding(horizontal = 24.dp, vertical = 20.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        TipoChip(pokemon.tipo)
        Column(Modifier.fillMaxWidth(), verticalArrangement = Arrangement.spacedBy(8.dp)) {
            DatoPokemon(stringResource(R.string.altura), "${pokemon.Altura} M")
            DatoPokemon(stringResource(R.string.peso), "${pokemon.Peso} KG")
            DatoPokemon(stringResource(R.string.habilidad), pokemon.Habilidad)
        }
        Text(pokemon.descripcion, fontSize = 18.sp, fontStyle = FontStyle.Italic, textAlign = TextAlign.Center)
        Spacer(Modifier.weight(1f))
        navegacion()
    }
}