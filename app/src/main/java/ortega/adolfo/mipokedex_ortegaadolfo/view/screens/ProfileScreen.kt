package ortega.adolfo.mipokedex_ortegaadolfo.view.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import ortega.adolfo.mipokedex_ortegaadolfo.R
import ortega.adolfo.mipokedex_ortegaadolfo.model.domain.Pokemon
import ortega.adolfo.mipokedex_ortegaadolfo.ui.theme.PokedexTheme
import ortega.adolfo.mipokedex_ortegaadolfo.utilities.TipoPokemon
import ortega.adolfo.mipokedex_ortegaadolfo.viewmodel.UserViewModel

@Composable
fun ProfileScreen(
    userViewModel: UserViewModel,
    modifier: Modifier = Modifier,
    innerPadding: PaddingValues = PaddingValues()
) {
    val companion by userViewModel.companion.collectAsStateWithLifecycle()

    ProfileContent(
        nombre = userViewModel.userName,
        correo = userViewModel.userEmail,
        companionPokemon = companion.pokemon,
        diasCompanero = companion.daysAsCompanion(),
        modifier = modifier,
        innerPadding = innerPadding
    )
}

@Composable
fun ProfileContent(
    nombre: String,
    correo: String,
    companionPokemon: Pokemon?,
    diasCompanero: Long,
    modifier: Modifier = Modifier,
    innerPadding: PaddingValues = PaddingValues()
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(innerPadding)
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(36.dp))

        Text(
            text = "Nombre: $nombre",
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = "Correo: $correo",
            style = MaterialTheme.typography.bodyLarge,
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(16.dp))

        if (companionPokemon == null) {
            Text(
                text = "Aún no tienes un compañero",
                style = MaterialTheme.typography.bodyLarge,
                textAlign = TextAlign.Center
            )
        } else {
            Image(
                painter = painterResource(id = companionPokemon.imgId),
                contentDescription = "Foto del pokemon compañero",
                modifier = Modifier.size(100.dp)
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "Compañero: ${companionPokemon.nombre}",
                style = MaterialTheme.typography.bodyLarge,
                fontWeight = FontWeight.SemiBold,
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = textoDias(diasCompanero),
                style = MaterialTheme.typography.bodyLarge,
                textAlign = TextAlign.Center
            )
        }
    }
}

private fun textoDias(dias: Long): String {
    return when (dias) {
        0L -> "Es tu compañero desde hoy"
        1L -> "Es tu compañero desde hace 1 día"
        else -> "Es tu compañero desde hace $dias días"
    }
}

@Preview(showBackground = true)
@Composable
fun ProfileScreenPreview() {
    PokedexTheme {
        ProfileContent(
            nombre = "Ash Ketchum",
            correo = "ash@pokemon.com",
            companionPokemon = Pokemon(
                nombre = "Pikachu",
                Altura = 0.4,
                Habilidad = "Electricidad Estática",
                Peso = 6,
                descripcion = "Pokémon eléctrico de ejemplo",
                numPokedex = "#0025",
                tipo = TipoPokemon.ELECTRICO,
                imgId = R.drawable.pikachu,
                favorite = true
            ),
            diasCompanero = 3
        )
    }
}