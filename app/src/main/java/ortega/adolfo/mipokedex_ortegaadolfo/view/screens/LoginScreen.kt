package ortega.adolfo.mipokedex_ortegaadolfo.view.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.text.input.TextFieldState
import androidx.compose.foundation.text.input.rememberTextFieldState
import androidx.compose.material3.Button
import androidx.compose.material3.SecureTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ortega.adolfo.mipokedex_ortegaadolfo.ui.theme.PokedexTheme
import ortega.adolfo.mipokedex_ortegaadolfo.viewmodel.UserViewModel

@Composable
fun LoginScreen(
    viewModel: UserViewModel,
    onLoginSuccess: () -> Unit,
    innerPadding: PaddingValues = PaddingValues()
) {
    if (viewModel.isLoggedIn()) {
        LaunchedEffect(Unit) { onLoginSuccess() }
    } else {
        LoginContent(
            email = viewModel.email,
            onEmailChange = viewModel::onEmailChange,
            passwordState = viewModel.passwordState,
            loginError = viewModel.loginError,
            errorDescription = viewModel.errorDescription,
            onLoginClick = {
                viewModel.login(viewModel.email, viewModel.passwordState.text.toString())
                if (!viewModel.loginError) {
                    onLoginSuccess()
                }
            },
            innerPadding = innerPadding
        )
    }
}

@Composable
fun LoginContent(
    email: String,
    onEmailChange: (String) -> Unit,
    passwordState: TextFieldState,
    loginError: Boolean,
    errorDescription: String,
    onLoginClick: () -> Unit,
    innerPadding: PaddingValues = PaddingValues()
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .padding(innerPadding),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        TextField(
            value = email,
            onValueChange = onEmailChange,
            placeholder = { Text("Correo electrónico") },
            singleLine = true,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email)
        )
        Spacer(Modifier.height(8.dp))
        SecureTextField(
            state = passwordState,
            placeholder = { Text("Contraseña") }
        )
        Spacer(Modifier.height(16.dp))
        Button(onClick = onLoginClick) {
            Text(text = "Iniciar sesión", fontWeight = FontWeight.Bold)
        }
        if (loginError) {
            Spacer(Modifier.height(8.dp))
            Text(
                text = errorDescription,
                color = Color.Red,
                fontStyle = FontStyle.Italic
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun LoginScreenPreview() {
    PokedexTheme {
        LoginContent(
            email = "",
            onEmailChange = {},
            passwordState = rememberTextFieldState(),
            loginError = true,
            errorDescription = "Usuario o contraseña incorrectos",
            onLoginClick = {}
        )
    }
}
