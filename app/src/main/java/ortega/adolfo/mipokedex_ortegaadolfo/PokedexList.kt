package ortega.adolfo.mipokedex_ortegaadolfo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.Scaffold
import ortega.adolfo.mipokedex_ortegaadolfo.navigation.MyApp
import ortega.adolfo.mipokedex_ortegaadolfo.ui.theme.PokedexTheme

class PokedexList : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PokedexTheme {
                Scaffold { innerPadding ->
                    MyApp(innerPadding = innerPadding)
                }
            }
        }
    }
}
