package ortega.adolfo.mipokedex_ortegaadolfo.model.data

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.core.handlers.ReplaceFileCorruptionHandler
import androidx.datastore.dataStore

import kotlinx.coroutines.flow.Flow
import ortega.adolfo.mipokedex_ortegaadolfo.model.domain.CompanionData
import ortega.adolfo.mipokedex_ortegaadolfo.model.domain.Pokemon
import ortega.adolfo.mipokedex_ortegaadolfo.model.preferences.CompanionSerializer

val Context.companionDataStore: DataStore<CompanionData> by dataStore(
    fileName = "pokemon.json",
    serializer = CompanionSerializer,
    corruptionHandler = ReplaceFileCorruptionHandler{ CompanionData()}
)

class CompanionDataStore(private val context: Context) {

    val companionFlow: Flow<CompanionData> = context.companionDataStore.data

    suspend fun saveCompanion(pokemon: Pokemon) {
        context.companionDataStore.updateData {
            CompanionData(pokemon = pokemon, selectedAtMillis = System.currentTimeMillis() )
        }
    }
}
