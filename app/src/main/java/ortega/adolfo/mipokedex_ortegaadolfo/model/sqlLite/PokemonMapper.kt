package ortega.adolfo.mipokedex_ortegaadolfo.model.sqlLite

import android.content.ContentValues
import android.database.Cursor
import ortega.adolfo.mipokedex_ortegaadolfo.R
import ortega.adolfo.mipokedex_ortegaadolfo.model.domain.Pokemon
import ortega.adolfo.mipokedex_ortegaadolfo.utilities.TipoPokemon

class PokemonMapper {

    fun getPokemon(cursor: Cursor): Pokemon {
        val nameIndex = cursor.getColumnIndex(Contract.Pokemons.COLUMN_NAME_NAME)
        val numIndex = cursor.getColumnIndex(Contract.Pokemons.COLUMN_NAME_NUMPOKEDEX)
        val typeIndex = cursor.getColumnIndex(Contract.Pokemons.COLUMN_NAME_TYPE)
        val descIndex = cursor.getColumnIndex(Contract.Pokemons.COLUMN_NAME_DESCRIPTION)
        val heightIndex = cursor.getColumnIndex(Contract.Pokemons.COLUMN_NAME_HEIGHT)
        val weightIndex = cursor.getColumnIndex(Contract.Pokemons.COLUMN_NAME_WEIGHT)
        val favIndex = cursor.getColumnIndex(Contract.Pokemons.COLUMN_NAME_FAVORITE)
        val abilityIndex = cursor.getColumnIndex(Contract.Pokemons.COLUMN_NAME_ABILITY)
        val imgIndex = cursor.getColumnIndex(Contract.Pokemons.COLUMN_NAME_IMGID)

        val name = if (nameIndex != -1) cursor.getString(nameIndex) ?: "" else ""
        val number = if (numIndex != -1) cursor.getString(numIndex) ?: "" else ""
        val type = if (typeIndex != -1) cursor.getString(typeIndex) ?: "" else ""
        val descripcion = if (descIndex != -1) cursor.getString(descIndex) ?: "" else ""
        val height = if (heightIndex != -1) cursor.getDouble(heightIndex) else 0.0
        val weight = if (weightIndex != -1) cursor.getInt(weightIndex) else 0
        val favorite = if (favIndex != -1) cursor.getInt(favIndex) else 0
        val ability = if (abilityIndex != -1) cursor.getString(abilityIndex) ?: "" else ""
        val imageRes = if (imgIndex != -1 && !cursor.isNull(imgIndex)) {
            val res = cursor.getInt(imgIndex)
            if (res != 0) res else R.drawable.pikachu
        } else {
            R.drawable.pikachu
        }

        val tipoEnum = TipoPokemon.entries.firstOrNull {
            it.name.equals(type, ignoreCase = true) || it.etiqueta.equals(type, ignoreCase = true)
        } ?: TipoPokemon.ELECTRICO

        return Pokemon(
            nombre = name,
            numPokedex = number,
            Altura = height,
            Habilidad = ability,
            Peso = weight,
            descripcion = descripcion,
            tipo = tipoEnum,
            imgId = imageRes,
            favorite = favorite == 1
        )
    }

    fun getContentValues(pokemon: Pokemon): ContentValues {
        val values = ContentValues().apply {
            put(Contract.Pokemons.COLUMN_NAME_NUMPOKEDEX, pokemon.numPokedex)
            put(Contract.Pokemons.COLUMN_NAME_NAME, pokemon.nombre)
            put(Contract.Pokemons.COLUMN_NAME_HEIGHT, pokemon.Altura)
            put(Contract.Pokemons.COLUMN_NAME_ABILITY, pokemon.Habilidad)
            put(Contract.Pokemons.COLUMN_NAME_WEIGHT, pokemon.Peso)
            put(Contract.Pokemons.COLUMN_NAME_DESCRIPTION, pokemon.descripcion)
            put(Contract.Pokemons.COLUMN_NAME_TYPE, pokemon.tipo.name)
            put(Contract.Pokemons.COLUMN_NAME_IMGID, pokemon.imgId)
            put(Contract.Pokemons.COLUMN_NAME_FAVORITE, if (pokemon.favorite) 1 else 0)
        }
        return values
    }
}
