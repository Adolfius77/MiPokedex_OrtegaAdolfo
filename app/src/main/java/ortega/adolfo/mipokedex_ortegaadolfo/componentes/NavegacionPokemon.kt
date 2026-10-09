package ortega.adolfo.mipokedex_ortegaadolfo.componentes

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ortega.adolfo.mipokedex_ortegaadolfo.Domain.Pokemon
import ortega.adolfo.mipokedex_ortegaadolfo.ui.theme.Gris

@Composable
fun NavegacionPokemon(
    anterior: Pokemon, siguiente: Pokemon,
    onAnterior: () -> Unit, onSiguiente: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        BotonFlecha("<", onAnterior)
        MiniaturaPokemon(anterior)
        MiniaturaPokemon(siguiente)
        BotonFlecha(">", onSiguiente)
    }
}

@Composable
fun MiniaturaPokemon(pokemon: Pokemon, modifier: Modifier = Modifier) {
    Column(modifier = modifier, horizontalAlignment = Alignment.CenterHorizontally) {
        Image(painterResource(pokemon.imgId), pokemon.nombre, Modifier.size(72.dp, 90.dp))
        Text("${pokemon.nombre} ${pokemon.numPokedex}")
    }
}

@Composable
fun BotonFlecha(texto: String, onClick: () -> Unit, modifier: Modifier = Modifier) {
    Button(
        onClick = onClick,
        modifier = modifier.size(44.dp),
        shape = CircleShape,
        contentPadding = PaddingValues(0.dp),
        colors = ButtonDefaults.buttonColors(containerColor = Gris, contentColor = Color.White)
    ) { Text(texto, fontSize = 24.sp) }
}