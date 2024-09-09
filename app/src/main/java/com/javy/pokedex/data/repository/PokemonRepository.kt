package com.javy.pokedex.data.repository

import com.javy.pokedex.data.remote.rest.PokemonRemoteRESTDataSource
import com.javy.pokedex.model.ui.Pokemon
import com.javy.pokedex.model.ui.Type
import dagger.hilt.android.scopes.ActivityRetainedScoped
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@ActivityRetainedScoped
class PokemonRepository @Inject constructor(private val dataSource: PokemonRemoteRESTDataSource) {

    suspend fun pokemon(): Flow<List<Pokemon>> =
        dataSource.getPokemon()

    suspend fun fetchPokemonModels(pokemon: List<Pokemon>): Flow<Pokemon> =
        dataSource.pokemonModels(pokemon)

    suspend fun fetchTypes(): Flow<List<Type>> =
        dataSource.types()

    suspend fun fetchPokemonByType(id: String): Flow<List<Pokemon>> =
        dataSource.pokemonByType(id)

    suspend fun pokemonById(id: String): Pokemon? =
        dataSource.pokemonById(id)
}