package ortega.adolfo.mipokedex_ortegaadolfo.pokemones

import ortega.adolfo.mipokedex_ortegaadolfo.R

object PokemonRepositorio {
    val pokemones: List<Pokemon> = listOf(
        Pokemon("Pikachu", 0.4, "Electricidad Estática", 6, "Cuando varios de estos Pokémon se juntan, la energía eléctrica que acumulan puede causar fuertes tormentas.", "#0025", TipoPokemon.ELECTRICO, R.drawable.pikachu, false),
        Pokemon("Lucario", 1.2, "Foco Interno", 54, "Puede leer el aura de sus rivales y así predecir todos sus movimientos.", "#0448", TipoPokemon.LUCHA_ACERO, R.drawable.lucario, false),
        Pokemon("Raichu", 0.8, "Electricidad Estática", 30, "Su larga cola le sirve para protegerse a sí mismo de su propio alto voltaje.", "#0026", TipoPokemon.ELECTRICO, R.drawable.raichu, false),
        Pokemon("Bulbasaur", 0.7, "Espesura", 7, "Este Pokémon nace con una semilla en el lomo que brota con el paso del tiempo.", "#0001", TipoPokemon.PLANTA_VENENO, R.drawable.bulbasor, false),
        Pokemon("Arbok", 3.5, "Intimidación", 65, "Las feroces señales de su panza han sido estudiadas. Se han confirmado 6 variaciones.", "#0024", TipoPokemon.VENENO, R.drawable.arbok, false)
    )
}