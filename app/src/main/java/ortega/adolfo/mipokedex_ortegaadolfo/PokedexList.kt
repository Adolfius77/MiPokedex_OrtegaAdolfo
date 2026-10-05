package ortega.adolfo.mipokedex_ortegaadolfo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import ortega.adolfo.mipokedex_ortegaadolfo.PokedexScreen.ui.MenuPokedex
import ortega.adolfo.mipokedex_ortegaadolfo.pokemones.PokemonRepositorio
import ortega.adolfo.mipokedex_ortegaadolfo.ui.theme.PokedexTheme

class PokedexList : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PokedexTheme {
                Scaffold { innerPadding ->
                    MenuPokedex(
                        pokemonList = PokemonRepositorio.pokemones,
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}
