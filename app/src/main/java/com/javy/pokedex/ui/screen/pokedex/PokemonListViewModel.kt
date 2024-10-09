package com.javy.pokedex.ui.screen.pokedex

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.javy.pokedex.data.repository.PokemonRepository
import com.javy.pokedex.model.ui.Pokemon
import com.javy.pokedex.model.ui.Type
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PokemonListViewModel @Inject constructor(private val pokemonRepository: PokemonRepository) :
    ViewModel() {

    private val _uiState = MutableStateFlow(PokemonListUIState())
    val uiState = _uiState.asStateFlow()

    init {
        _uiState.update {
            it.copy(isLoading = true)
        }

        fetchTypes()
        fetchPokemon()
    }

    private fun fetchPokemon() {
        viewModelScope.launch {
            runCatching {
                pokemonRepository.pokemon()
            }.onSuccess { flow: Flow<List<Pokemon>> ->
                flow.collect { pokemon ->
                    _uiState.update {
                        it.copy(pokemon = pokemon, isLoading = false)
                    }
                    if (_uiState.value.requiresFullModels) {
                        fetchPokemonModels()
                    }
                }
            }.onFailure { e: Throwable ->
                val errorMessage = e.message
                Log.e("pokedex fetch pokemon", errorMessage, e)
                _uiState.update {
                    it.copy(errorMessage = errorMessage, isLoading = false)
                }
            }
        }
    }

    private fun fetchPokemonModels() {
        viewModelScope.launch {
            runCatching {
                pokemonRepository.fetchPokemonModels(_uiState.value.pokemon)
            }.onSuccess { flow: Flow<Pokemon> ->
                flow.collect { pokemon ->
                    val pokemonList = _uiState.value.pokemon.toMutableList()
                    pokemonList.forEachIndexed { index, current ->
                        if (current.id == pokemon.id) {
                            pokemonList[index] = pokemon
                        }
                    }
                    _uiState.update {
                        it.copy(pokemon = pokemonList)
                    }
                }
            }.onFailure { e: Throwable ->
                val errorMessage = e.message
                _uiState.update {
                    it.copy(errorMessage = errorMessage, isLoading = false)
                }
            }
        }
    }

    private fun fetchTypes() {
        viewModelScope.launch {
            runCatching {
                pokemonRepository.fetchTypes()
            }.onSuccess { flow: Flow<List<Type>> ->
                flow.collect { types ->
                    _uiState.update {
                        it.copy(types = types)
                    }
                    fetchPokemonModels()
                }
            }.onFailure { e: Throwable ->
                val errorMessage = e.message
                _uiState.update {
                    it.copy(errorMessage = errorMessage, isLoading = false)
                }
            }
        }
    }

    fun updateTypeFilter(selected: Type) {
        _uiState.update {
            it.copy(pokemon = emptyList(), isLoading = true)
        }
        _uiState.update {
            val types = _uiState.value.types.toMutableList()
            types.forEachIndexed { index, type ->
                types[index] = types[index].copy(
                    isActive = type.id == selected.id
                )
            }
            it.copy(types = types)
        }
        fetchPokemonByType(selected.id)
    }


    private fun fetchPokemonByType(id: String) {
        _uiState.update {
            it.copy(isLoading = true)
        }
        viewModelScope.launch {
            runCatching {
                pokemonRepository.fetchPokemonByType(id)
            }.onSuccess { flow: Flow<List<Pokemon>> ->
                flow.collect { pokemon ->
                    _uiState.update {
                        it.copy(pokemon = pokemon, isLoading = false)
                    }
                    fetchPokemonModels()
                }
            }.onFailure { e: Throwable ->
                val errorMessage = e.message
                _uiState.update {
                    it.copy(errorMessage = errorMessage, isLoading = false)
                }
            }
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
        get() = pokemon.isEmpty() && !isLoading

    val hasError: Boolean
        get() = errorMessage != null

    val requiresFullModels: Boolean
        get() = pokemon.firstOrNull()?.hasCompleteModel != true
}