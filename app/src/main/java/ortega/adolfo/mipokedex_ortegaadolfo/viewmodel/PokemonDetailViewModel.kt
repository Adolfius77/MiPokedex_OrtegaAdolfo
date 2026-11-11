package ortega.adolfo.mipokedex_ortegaadolfo.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.createSavedStateHandle
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import androidx.navigation.toRoute
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import ortega.adolfo.mipokedex_ortegaadolfo.model.data.CompanionDataStore
import ortega.adolfo.mipokedex_ortegaadolfo.model.data.PokemonRepositorio
import ortega.adolfo.mipokedex_ortegaadolfo.model.domain.Pokemon
import ortega.adolfo.mipokedex_ortegaadolfo.navigation.Routes

class PokemonDetailViewModel(
    savedStateHandle: SavedStateHandle,
    private val companionDataStore: CompanionDataStore
) : ViewModel() {

    private val numPokedex: String = savedStateHandle.toRoute<Routes.PokemonDetail>().numPokedex

    val pokemon: Pokemon? = PokemonRepositorio.getPokemonByNumber(numPokedex)

    val isCompanion: StateFlow<Boolean> = companionDataStore.companionFlow
        .map { it.pokemon?.numPokedex == numPokedex }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = false
        )

    var showSelectedMessage by mutableStateOf(false)
        private set

    fun selectCompanion() {
        val selected = pokemon ?: return
        viewModelScope.launch {
            companionDataStore.saveCompanion(selected)
            showSelectedMessage = true
        }
    }

    fun messageShown() {
        showSelectedMessage = false
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = checkNotNull(this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY])
                PokemonDetailViewModel(
                    savedStateHandle = createSavedStateHandle(),
                    companionDataStore = CompanionDataStore(application)
                )
            }
        }
    }
}