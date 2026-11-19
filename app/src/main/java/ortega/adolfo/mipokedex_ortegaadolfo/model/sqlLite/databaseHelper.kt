package ortega.adolfo.mipokedex_ortegaadolfo.model.sqlLite

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DatabaseHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    override fun onCreate(db: SQLiteDatabase?) {
        val createPokemons = "CREATE TABLE ${Contract.Pokemons.TABLE_NAME} (" +
                "${Contract.Pokemons.COLUMN_NAME_ID} INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "${Contract.Pokemons.COLUMN_NAME_NUMPOKEDEX} TEXT, " +
                "${Contract.Pokemons.COLUMN_NAME_NAME} TEXT, " +
                "${Contract.Pokemons.COLUMN_NAME_DESCRIPTION} TEXT, " +
                "${Contract.Pokemons.COLUMN_NAME_HEIGHT} REAL, " +
                "${Contract.Pokemons.COLUMN_NAME_ABILITY} TEXT, " +
                "${Contract.Pokemons.COLUMN_NAME_WEIGHT} INTEGER, " +
                "${Contract.Pokemons.COLUMN_NAME_IMGID} INTEGER, " +
                "${Contract.Pokemons.COLUMN_NAME_FAVORITE} INTEGER, " +
                "${Contract.Pokemons.COLUMN_NAME_TYPE} TEXT)".trimIndent()
        db?.execSQL(createPokemons)
    }

    override fun onUpgrade(
        db: SQLiteDatabase?,
        oldVersion: Int,
        newVersion: Int
    ) {
        val dropTablePokemons = "DROP TABLE IF EXISTS ${Contract.Pokemons.TABLE_NAME}"
        db?.execSQL(dropTablePokemons)
        onCreate(db)
    }

    companion object {
        private const val DATABASE_NAME = "pokedex.db"
        private const val DATABASE_VERSION = 1
    }
}

typealias databaseHelper = DatabaseHelper
