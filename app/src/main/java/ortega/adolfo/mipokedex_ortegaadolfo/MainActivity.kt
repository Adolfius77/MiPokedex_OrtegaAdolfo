package ortega.adolfo.mipokedex_ortegaadolfo

import android.graphics.Color
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import ortega.adolfo.mipokedex_ortegaadolfo.pokemones.Pokemon

class MainActivity : AppCompatActivity() {
    private var indice = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)

            val nombre: TextView = findViewById<TextView>(R.id.txt_nombre)
            val peso: TextView = findViewById<TextView>(R.id.txt_peso)
            val habilidad: TextView = findViewById<TextView>(R.id.txt_habilida)
            val numPoke: TextView = findViewById<TextView>(R.id.txt_numero)
            val tipo: TextView = findViewById<TextView>(R.id.txt_tipo)
            val caract: TextView = findViewById<TextView>(R.id.txt_caracteristicas)
            val altura: TextView = findViewById<TextView>(R.id.altura_txt)
            val pokeImg: android.widget.ImageView = findViewById(R.id.poke_img)


            val img_atras : ImageView = findViewById<ImageView>(R.id.img_atras)
            val img_sig: ImageView = findViewById<ImageView>(R.id.img_sig)


            val txt_atras: TextView = findViewById<TextView>(R.id.txt_atras)
            val txt_sig: TextView = findViewById<TextView>(R.id.txt_sig)

            val btnAtras : Button = findViewById<Button>(R.id.btn_atras)
            val btnSig : Button = findViewById<Button>(R.id.btn_sig)



            val listaPokemones = listOf(
                Pokemon("Pikachu", 0.4, "Electricidad Estática", 6, "Cuando varios de estos Pokémon se juntan, la energía eléctrica que acumulan puede causar fuertes tormentas.", "#0025", "Eléctrico", "pikachu"),
                Pokemon("Lucario", 1.2, "Foco Interno", 54, "Puede leer el aura de sus rivales y así predecir todos sus movimientos.", "#0448", "Lucha / Acero", "lucario"),
                Pokemon("Raichu", 0.8, "Electricidad Estática", 30, "Su larga cola le sirve para protegerse a sí mismo de su propio alto voltaje.", "#0026", "Eléctrico", "raichu"),
                Pokemon("Bulbasaur", 0.7, "Espesura", 7, "Este Pokémon nace con una semilla en el lomo que brota con el paso del tiempo.", "#0001", "Planta / Veneno", "bulbasor"),
                Pokemon("Arbok", 3.5, "Intimidación", 65, "Las feroces señales de su panza han sido estudiadas. Se han confirmado 6 variaciones.", "#0024", "Veneno", "arbok"),


            )

            fun Actualizar(){
                val p = listaPokemones[indice]
                nombre.text = p.nombre
                peso.text = "${p.Peso} KG"
                habilidad.text = p.Habilidad
                numPoke.text = p.numPokedex
                tipo.text = p.tipo
                caract.text = p.descripcion
                altura.text = "${p.Altura} M"

                val resourceId = resources.getIdentifier(p.imgId, "drawable", packageName)
                pokeImg.setImageResource(resourceId)

                val indiceAtras = if(indice == 0) listaPokemones.size - 1 else indice -1
                val pagAtras = listaPokemones[indiceAtras]
                txt_atras.text = pagAtras.nombre + " "+ "${p.numPokedex}"
                val resAtras = resources.getIdentifier(pagAtras.imgId,"drawable", packageName)
                img_atras.setImageResource(resAtras)

                val indiceSig = if(indice == listaPokemones.size -1) 0 else indice + 1
                val pagSig = listaPokemones[indiceSig]
                txt_sig.text = pagSig.nombre +" " + "${p.numPokedex}"
                val resSig = resources.getIdentifier(pagSig.imgId, "drawable", packageName)
                img_sig.setImageResource(resSig)

                when(p.tipo){
                    "Eléctrico" -> {
                        tipo.setBackgroundColor(this@MainActivity.getColor(R.color.amarillo))
                        tipo.setTextColor(this@MainActivity.getColor(R.color.black_bajti))
                    }
                    "Lucha / Acero" -> {
                        tipo.setBackgroundColor(this@MainActivity.getColor(R.color.cafe))
                        tipo.setTextColor(this@MainActivity.getColor(R.color.black_bajti))
                    }
                    "Planta / Veneno" -> {
                        tipo.setBackgroundColor(this@MainActivity.getColor(R.color.morado))
                        tipo.setTextColor(this@MainActivity.getColor(R.color.off_white))
                    }
                }

            }
            Actualizar()

            btnAtras.setOnClickListener {
                indice = if (indice == 0)listaPokemones.size - 1 else indice -1
                Actualizar()
            }
            btnSig.setOnClickListener {
                indice = if (indice == listaPokemones.size - 1) 0 else indice +1
                Actualizar()
            }




            insets
        }
    }
}