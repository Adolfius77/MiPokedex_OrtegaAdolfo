package ortega.adolfo.mipokedex_ortegaadolfo.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

val Amarillo = Color(0xFFE2C731)
val Rojo = Color(0xFFE23131)
val OffWhite = Color(0xFFF4FEFE)
val BlackBajti = Color(0xFF0F0F0F)
val Gris = Color(0xFF3A3838)
val Cafe = Color(0xFFB18A6B)
val Morado = Color(0xFF673AB7)
val Verde = Color(0xFF4E8A3E)

private val EsquemaPokedex = lightColorScheme(
    primary = Rojo, secondary = Amarillo, background = Amarillo,
    surface = Color.White, onSurface = BlackBajti
)

@Composable
fun PokedexTheme(content: @Composable () -> Unit) {
    MaterialTheme(colorScheme = EsquemaPokedex, content = content)
}