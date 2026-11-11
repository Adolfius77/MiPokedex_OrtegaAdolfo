package ortega.adolfo.mipokedex_ortegaadolfo.model.data

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.dataStore
import kotlinx.coroutines.flow.Flow
import ortega.adolfo.mipokedex_ortegaadolfo.model.domain.Pokemon
import ortega.adolfo.mipokedex_ortegaadolfo.model.preferences.PokemonSerializer

val Context.pokemonDataStore: DataStore<Pokemon> by dataStore(
    fileName = "pokemon.json",
    serializer = PokemonSerializer
)

class CompanionDataStore(private val context: Context) {

    val pokemonFlow: Flow<Pokemon> = context.pokemonDataStore.data

    suspend fun saveCompanion(pokemon: Pokemon) {
        context.pokemonDataStore.updateData {
            pokemon
        }
    }
}
