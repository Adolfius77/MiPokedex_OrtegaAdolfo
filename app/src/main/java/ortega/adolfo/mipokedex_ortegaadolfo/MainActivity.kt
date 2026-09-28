package ortega.adolfo.mipokedex_ortegaadolfo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import ortega.adolfo.mipokedex_ortegaadolfo.PokedexScreen.ui.PokedexScreen
import ortega.adolfo.mipokedex_ortegaadolfo.ui.theme.PokedexTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PokedexTheme { PokedexScreen() }
        }
    }
}
