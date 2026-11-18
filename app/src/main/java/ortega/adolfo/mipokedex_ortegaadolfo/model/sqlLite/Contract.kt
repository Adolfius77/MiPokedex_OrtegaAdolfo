package ortega.adolfo.mipokedex_ortegaadolfo.model.sqlLite

import android.provider.BaseColumns

object Contract {
    object Pokemons: BaseColumns{
        const val TABLE_NAME = "pokemons"
        const val COLUMN_NAME_ID = "_id"
        const val COLUMN_NAME_NUMPOKEDEX = "numPokedex"
        const val COLUMN_NAME_NAME = "name"
        const val COLUMN_NAME_HEIGHT = "height"
        const val COLUMN_NAME_ABILITY = "ability"
        const val COLUMN_NAME_WEIGHT = "weight"
        const val COLUMN_NAME_DESCRIPTION = "description"
        const val COLUMN_NAME_TYPE = "type"
        const val COLUMN_NAME_IMGID = "imgId"
        const val COLUMN_NAME_FAVORITE = "favorite"

    }
}