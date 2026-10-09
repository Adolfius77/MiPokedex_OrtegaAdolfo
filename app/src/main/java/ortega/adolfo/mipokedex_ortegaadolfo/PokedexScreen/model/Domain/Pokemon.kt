package ortega.adolfo.mipokedex_ortegaadolfo.PokedexScreen.model.Domain

import androidx.annotation.DrawableRes
import androidx.compose.runtime.Immutable
import ortega.adolfo.mipokedex_ortegaadolfo.PokedexScreen.utilities.TipoPokemon

@Immutable
data class Pokemon(
    val nombre: String,
    val Altura: Double,
    val Habilidad: String,
    val Peso: Int,
    val descripcion: String,
    val numPokedex: String,
    val tipo: TipoPokemon,
    @DrawableRes val imgId: Int,
    val favorite: Boolean
)
