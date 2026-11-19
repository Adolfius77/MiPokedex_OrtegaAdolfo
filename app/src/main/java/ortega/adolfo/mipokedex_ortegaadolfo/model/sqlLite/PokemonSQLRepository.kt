package ortega.adolfo.mipokedex_ortegaadolfo.model.sqlLite

import ortega.adolfo.mipokedex_ortegaadolfo.model.data.interfaces.IPokemonDAO
import ortega.adolfo.mipokedex_ortegaadolfo.model.data.interfaces.IPokemonRepository
import ortega.adolfo.mipokedex_ortegaadolfo.model.domain.Pokemon

class PokemonSQLRepository(
    private val pokemonDAO: IPokemonDAO
) : IPokemonRepository {

    override suspend fun insertPokemon(pokemon: Pokemon) {
        if (pokemon.nombre.isBlank() || pokemon.numPokedex.isBlank()) {
            throw IllegalArgumentException("El nombre y el número de Pokédex no pueden estar vacíos")
        }
        pokemonDAO.insertPokemon(pokemon)
    }

    override suspend fun getAllPokemons(): List<Pokemon> {
        return pokemonDAO.getAllPokemons()
    }

    override suspend fun getPokemonById(id: Int): Pokemon? {
        return pokemonDAO.getPokemonById(id)
    }

    override suspend fun getFavoritePokemons(): List<Pokemon> {
        return pokemonDAO.getFavoritePokemons()
    }

    override suspend fun getPokemonsByType(type: String): List<Pokemon> {
        return pokemonDAO.getPokemonsByType(type)
    }

    override suspend fun updatePokemon(pokemon: Pokemon) {
        if (pokemon.nombre.isBlank() || pokemon.numPokedex.isBlank()) {
            throw IllegalArgumentException("El nombre y el número de Pokédex no pueden estar vacíos")
        }
        pokemonDAO.updatePokemon(pokemon)
    }

    override suspend fun deletePokemon(pokemon: Pokemon) {
        pokemonDAO.deletePokemon(pokemon)
    }
}
