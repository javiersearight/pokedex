package com.javy.pokedex.ui.screen.pokemondetail

import androidx.lifecycle.ViewModel
import com.javy.pokedex.data.repository.PokemonRepository
import com.javy.pokedex.model.ui.Pokemon
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class PokemonDetailViewModel @Inject constructor(
    private val pokemonRepository: PokemonRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(PokemonDetailUIState())
    val uiState = _uiState.asStateFlow()

    init {
        _uiState.update {
            it.copy(isLoading = true)
        }
    }

    fun fetchPokemonById(id: String) {
        val pokemon = pokemonRepository.pokemonById(id)
        _uiState.update {
            it.copy(pokemon = pokemon, isLoading = false)
        }
    }
}


data class PokemonDetailUIState(
    val pokemon: Pokemon? = null,
    val errorMessage: String? = null,
    val isLoading: Boolean = false
) {
    val isEmpty: Boolean
        get() = pokemon == null

    val hasError: Boolean
        get() = errorMessage != null
}