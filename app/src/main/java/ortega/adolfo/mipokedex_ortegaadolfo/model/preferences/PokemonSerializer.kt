package ortega.adolfo.mipokedex_ortegaadolfo.model.preferences

import androidx.datastore.core.CorruptionException
import androidx.datastore.core.Serializer
import kotlinx.serialization.SerializationException
import kotlinx.serialization.json.Json
import ortega.adolfo.mipokedex_ortegaadolfo.R
import ortega.adolfo.mipokedex_ortegaadolfo.model.domain.Pokemon
import ortega.adolfo.mipokedex_ortegaadolfo.utilities.TipoPokemon
import java.io.InputStream
import java.io.OutputStream

object PokemonSerializer : Serializer<Pokemon> {
    override val defaultValue: Pokemon = Pokemon(
        nombre = "",
        Altura = 0.0,
        Habilidad = "",
        Peso = 0,
        descripcion = "",
        numPokedex = "",
        tipo = TipoPokemon.ELECTRICO,
        imgId = R.drawable.pikachu,
        favorite = false
    )

    override suspend fun readFrom(input: InputStream): Pokemon {
        return try {
            Json.decodeFromString(
                Pokemon.serializer(), input.readBytes().decodeToString()
            )
        } catch (exception: SerializationException) {
            throw CorruptionException("No se pudo leer el JSON", exception)
        }
    }

    override suspend fun writeTo(t: Pokemon, output: OutputStream) {
        output.write(Json.encodeToString(Pokemon.serializer(), t).encodeToByteArray())
    }
}
