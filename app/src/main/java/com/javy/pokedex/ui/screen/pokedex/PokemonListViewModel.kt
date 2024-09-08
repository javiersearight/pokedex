package com.javy.pokedex.ui.screen.pokedex

import androidx.lifecycle.ViewModel
import com.javy.pokedex.data.repository.PokemonRepository
import com.javy.pokedex.model.ui.Pokemon
import com.javy.pokedex.model.ui.Type
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class PokemonListViewModel : ViewModel() {

    private val pokemonRepository = PokemonRepository()

    private val _uiState = MutableStateFlow(PokemonListUIState())
    val uiState = _uiState.asStateFlow()

    init {
        _uiState.update {
            it.copy(isLoading = true)
        }

        fetchTypes()
        fetchPokemon()
    }

    private fun fetchTypes() {
        val types = pokemonRepository.types()
        _uiState.update {
            it.copy(types = types)
        }
    }

    private fun fetchPokemon() {
        val pokemon = pokemonRepository.pokemon()
        _uiState.update {
            it.copy(pokemon = pokemon, isLoading = false)
        }
    }

    fun updateTypeFilter(selected: Type) {
        _uiState.update {
            it.copy(isLoading = true)
        }
        _uiState.update {
            val types = _uiState.value.types.toMutableList()
            types.forEachIndexed { index, type ->
                types[index] = types[index].copy(
                    isActive = type.id == selected.id)
            }
            it.copy(types = types)
        }
        fetchPokemonByType(selected)
    }

    private fun fetchPokemonByType(type: Type) {
        val pokemon = pokemonRepository.pokemonByType(type)
        _uiState.update {
            it.copy(pokemon = pokemon, isLoading = false)
        }
    }
}

data class PokemonListUIState(
    val pokemon: List<Pokemon> = emptyList(),
    val types: List<Type> = emptyList(),
    val errorMessage: String? = null,
    val isLoading: Boolean = false
) {
    val isEmpty: Boolean
        get() = pokemon.isEmpty()

    val hasError: Boolean
        get() = errorMessage != null
}