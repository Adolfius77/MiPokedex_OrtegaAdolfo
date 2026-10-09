package ortega.adolfo.mipokedex_ortegaadolfo.utilities

import androidx.compose.ui.graphics.Color
import ortega.adolfo.mipokedex_ortegaadolfo.ui.theme.Amarillo
import ortega.adolfo.mipokedex_ortegaadolfo.ui.theme.BlackBajti
import ortega.adolfo.mipokedex_ortegaadolfo.ui.theme.Cafe
import ortega.adolfo.mipokedex_ortegaadolfo.ui.theme.Morado
import ortega.adolfo.mipokedex_ortegaadolfo.ui.theme.OffWhite
import ortega.adolfo.mipokedex_ortegaadolfo.ui.theme.Verde

enum class TipoPokemon(val etiqueta: String, val fondo: Color, val texto: Color) {
    ELECTRICO("Eléctrico", Amarillo, BlackBajti),
    LUCHA_ACERO("Lucha / Acero", Cafe, BlackBajti),
    PLANTA_VENENO("Planta / Veneno", Morado, OffWhite),
    VENENO("Veneno", Verde, OffWhite)
}