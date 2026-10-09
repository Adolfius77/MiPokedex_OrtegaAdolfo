package ortega.adolfo.mipokedex_ortegaadolfo.PokedexScreen.model.Domain

data class PokedexState(
    val team: List<Pokemon> = emptyList(),
    val lasCaptured: Pokemon? = null
)
