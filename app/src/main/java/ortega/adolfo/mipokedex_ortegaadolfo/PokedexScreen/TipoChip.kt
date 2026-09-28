package ortega.adolfo.mipokedex_ortegaadolfo.PokedexScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ortega.adolfo.mipokedex_ortegaadolfo.pokemones.TipoPokemon

@Composable
fun TipoChip(tipo: TipoPokemon,modifier: Modifier = Modifier){
    Text(
        text = tipo.etiqueta,
        color = tipo.texto,
        fontSize = 20.sp,
        modifier = modifier.background(tipo.fondo, RoundedCornerShape(50)).padding(horizontal = 16.dp, vertical = 4.dp)
    )
}
@Preview
@Composable
private fun TipoChipPreview(){
    TipoChip(TipoPokemon.PLANTA_VENENO)
}