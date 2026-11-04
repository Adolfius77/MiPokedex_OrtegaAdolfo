package ortega.adolfo.mipokedex_ortegaadolfo.viewmodel

import androidx.compose.foundation.text.input.TextFieldState
import androidx.compose.foundation.text.input.clearText
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import ortega.adolfo.mipokedex_ortegaadolfo.model.data.Users

class UserViewModel(users: Users) : ViewModel() {

    val users: Users = users

    var email by mutableStateOf("")
        private set

    val passwordState = TextFieldState()

    var loginError by mutableStateOf(false)
        private set

    var errorDescription by mutableStateOf("")
        private set

    fun onEmailChange(newEmail: String) {
        email = newEmail
    }

    fun login(mail: String, password: String) {
        val user = users.loginService(mail.trim(), password)
        if (user == null) {
            loginError = true
            errorDescription = "Usuario o contraseña incorrectos"
        } else {
            loginError = false
            errorDescription = ""
        }
    }

    fun isLoggedIn(): Boolean {
        return users.isLoggedIn()
    }

    fun logout() {
        users.logout()
        email = ""
        passwordState.clearText()
        loginError = false
        errorDescription = ""
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = checkNotNull(this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY])
                UserViewModel(Users(application))
            }
        }
    }
}
