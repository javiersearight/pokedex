package com.javy.pokedex.data.repository

import com.javy.pokedex.data.mock.PokemonMockDataSource
import com.javy.pokedex.data.remote.rest.PokemonRemoteRESTDataSource
import com.javy.pokedex.model.ui.Pokemon
import com.javy.pokedex.model.ui.Type
import dagger.hilt.android.scopes.ActivityRetainedScoped
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@ActivityRetainedScoped
class PokemonRepository @Inject constructor(private val dataSource: PokemonRemoteRESTDataSource) {
    private val mockDataSource = PokemonMockDataSource()

    suspend fun pokemon(): Flow<List<Pokemon>> =
        dataSource.getPokemon()

    suspend fun fetchPokemonModels(pokemon: List<Pokemon>): Flow<Pokemon> =
        dataSource.pokemonModels(pokemon)

    suspend fun fetchTypes(): Flow<List<Type>> =
        dataSource.types()

    suspend fun fetchPokemonByType(id: String): Flow<List<Pokemon>> =
        dataSource.pokemonByType(id)

    fun pokemonById(id: String): Pokemon? =
        mockDataSource.pokemonById(id)
}