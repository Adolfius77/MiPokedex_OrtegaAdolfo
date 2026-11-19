package ortega.adolfo.mipokedex_ortegaadolfo.model.sqlLite

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import ortega.adolfo.mipokedex_ortegaadolfo.model.data.interfaces.IPokemonDAO
import ortega.adolfo.mipokedex_ortegaadolfo.model.domain.Pokemon

class PokemonDAO(
    private val dbHelper: DatabaseHelper
) : IPokemonDAO {

    private val mapper = PokemonMapper()

    override suspend fun insertPokemon(pokemon: Pokemon): Unit = withContext(Dispatchers.IO) {
        val db = dbHelper.writableDatabase
        val values = mapper.getContentValues(pokemon)
        db.insert(Contract.Pokemons.TABLE_NAME, null, values)
    }

    override suspend fun getAllPokemons(): List<Pokemon> = withContext(Dispatchers.IO) {
        val pokemons = mutableListOf<Pokemon>()
        val db = dbHelper.readableDatabase
        val cursor = db.query(
            Contract.Pokemons.TABLE_NAME,
            null,
            null,
            null,
            null,
            null,
            null
        )
        cursor.use { c ->
            while (c.moveToNext()) {
                pokemons.add(mapper.getPokemon(c))
            }
        }
        pokemons
    }

    override suspend fun getPokemonById(id: Int): Pokemon? = withContext(Dispatchers.IO) {
        val db = dbHelper.readableDatabase
        val cursor = db.query(
            Contract.Pokemons.TABLE_NAME,
            null,
            "${Contract.Pokemons.COLUMN_NAME_ID} = ?",
            arrayOf(id.toString()),
            null,
            null,
            null
        )
        cursor.use { c ->
            if (c.moveToFirst()) {
                mapper.getPokemon(c)
            } else null
        }
    }

    override suspend fun getFavoritePokemons(): List<Pokemon> = withContext(Dispatchers.IO) {
        val db = dbHelper.readableDatabase
        val cursor = db.query(
            Contract.Pokemons.TABLE_NAME,
            null,
            "${Contract.Pokemons.COLUMN_NAME_FAVORITE} = ?",
            arrayOf("1"),
            null,
            null,
            null
        )
        val pokemons = mutableListOf<Pokemon>()
        cursor.use { c ->
            while (c.moveToNext()) {
                pokemons.add(mapper.getPokemon(c))
            }
        }
        pokemons
    }

    override suspend fun getPokemonsByType(type: String): List<Pokemon> = withContext(Dispatchers.IO) {
        val db = dbHelper.readableDatabase
        val cursor = db.query(
            Contract.Pokemons.TABLE_NAME,
            null,
            "${Contract.Pokemons.COLUMN_NAME_TYPE} = ?",
            arrayOf(type),
            null,
            null,
            null
        )
        val pokemons = mutableListOf<Pokemon>()
        cursor.use { c ->
            while (c.moveToNext()) {
                pokemons.add(mapper.getPokemon(c))
            }
        }
        pokemons
    }

    override suspend fun updatePokemon(pokemon: Pokemon): Unit = withContext(Dispatchers.IO) {
        val db = dbHelper.writableDatabase
        val values = mapper.getContentValues(pokemon)
        db.update(
            Contract.Pokemons.TABLE_NAME,
            values,
            "${Contract.Pokemons.COLUMN_NAME_NUMPOKEDEX} = ?",
            arrayOf(pokemon.numPokedex)
        )
    }

    override suspend fun deletePokemon(pokemon: Pokemon): Unit = withContext(Dispatchers.IO) {
        val db = dbHelper.writableDatabase
        db.delete(
            Contract.Pokemons.TABLE_NAME,
            "${Contract.Pokemons.COLUMN_NAME_NUMPOKEDEX} = ?",
            arrayOf(pokemon.numPokedex)
        )
    }
}
