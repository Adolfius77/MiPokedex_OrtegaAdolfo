package ortega.adolfo.mipokedex_ortegaadolfo.model.domain

data class PokedexState(
    val team: List<Pokemon> = emptyList(),
    val lasCaptured: Pokemon? = null
)
