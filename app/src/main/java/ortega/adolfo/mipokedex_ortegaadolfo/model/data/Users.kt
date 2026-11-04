package ortega.adolfo.mipokedex_ortegaadolfo.model.data

import android.content.Context
import ortega.adolfo.mipokedex_ortegaadolfo.model.domain.User
import ortega.adolfo.mipokedex_ortegaadolfo.model.preferences.ARCHIVO
import ortega.adolfo.mipokedex_ortegaadolfo.model.preferences.POKEMON_DISPLAY
import ortega.adolfo.mipokedex_ortegaadolfo.model.preferences.USER
import ortega.adolfo.mipokedex_ortegaadolfo.model.preferences.USERNAME

class Users(context: Context) {

    val users = listOf(
        User(email = "ash@pokemon.com", password = "pikachu", name = "Ash Ketchum"),
        User(email = "misty@pokemon.com", password = "staryu", name = "Misty"),
        User(email = "brock@pokemon.com", password = "onix", name = "Brock")
    )

    val shared = context.getSharedPreferences(ARCHIVO, Context.MODE_PRIVATE)

    fun getUserByMail(mail: String): User? {
        return users.find { it.email.equals(mail, ignoreCase = true) }
    }

    fun loginService(email: String, password: String): User? {
        val user = getUserByMail(email)
        if (user != null && user.password == password) {
            saveUser(user.email, user.name)
            return user
        }
        return null
    }

    fun saveUser(user: String, name: String) {
        val editor = shared.edit()
        editor.putString(USER, user)
        editor.putString(USERNAME, name)
        editor.apply()
    }

    fun isLoggedIn(): Boolean {
        val user = shared.getString(USER, null)
        val username = shared.getString(USERNAME, null)

        if (user != null && username != null) {
            return true
        }
        return false
    }

    fun logout() {
        val editor = shared.edit()
        editor.putString(USER, null)
        editor.putString(USERNAME, null)
        editor.apply()
    }

    fun saveDisplayPreference(grid: Boolean) {
        val editor = shared.edit()
        editor.putBoolean(POKEMON_DISPLAY, grid)
        editor.apply()
    }

    fun isGridDisplay(): Boolean {
        return shared.getBoolean(POKEMON_DISPLAY, false)
    }
}
