package ortega.adolfo.mipokedex_ortegaadolfo.model.preferences

import androidx.datastore.core.CorruptionException
import androidx.datastore.core.Serializer
import kotlinx.serialization.SerializationException
import kotlinx.serialization.json.Json
import ortega.adolfo.mipokedex_ortegaadolfo.model.domain.CompanionData
import java.io.InputStream
import java.io.OutputStream

object CompanionSerializer : Serializer<CompanionData> {
    private val json = Json { ignoreUnknownKeys = true }

    override val defaultValue: CompanionData = CompanionData()

    override suspend fun readFrom(input: InputStream): CompanionData {
        return try {
            json.decodeFromString(
                CompanionData.serializer(), input.readBytes().decodeToString()
            )
        } catch (exception: SerializationException) {
            throw CorruptionException("No se pudo leer el JSON", exception)
        }
    }

    override suspend fun writeTo(t: CompanionData, output: OutputStream) {
        output.write(json.encodeToString(CompanionData.serializer(), t).encodeToByteArray())
    }
}