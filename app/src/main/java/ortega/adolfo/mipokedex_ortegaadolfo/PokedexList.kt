package ortega.adolfo.mipokedex_ortegaadolfo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import ortega.adolfo.mipokedex_ortegaadolfo.navigation.RootApp

class PokedexList : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            RootApp()
        }
    }
}
