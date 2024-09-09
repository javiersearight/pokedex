package com.javy.pokedex.data.remote.rest

import com.javy.pokedex.com.javy.pokedex.data.remote.rest.model.Item
import com.javy.pokedex.data.remote.rest.model.PokemonResult
import com.javy.pokedex.model.ui.Pokemon
import com.javy.pokedex.model.ui.Type
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class PokemonRemoteRESTDataSource @Inject constructor(private val apiService: PokemonApiService) {

    suspend fun getPokemon(): Flow<List<Pokemon>> {
        return flow {
            val response = apiService.pokemon()
            val results = response.body()?.results
            val pokemon = results?.map {
                it.pokemon()
            } ?: emptyList()
            emit(pokemon)
        }.flowOn(Dispatchers.IO)
    }

    suspend fun pokemonModels(pokemon: List<Pokemon>): Flow<Pokemon> {
        return flow {
            pokemon.forEach {
                val response = apiService.pokemonById(it.id)
                val pokemonResult = response.body()
                if (pokemonResult != null) {
                    emit(pokemonResult.pokemon())
                }
            }
        }.flowOn(Dispatchers.IO)
    }

    suspend fun types(): Flow<List<Type>> {
        return flow {
            val response = apiService.types()
            val results = response.body()?.results
            val types = results?.map {
                it.type()
            }
            emit(types ?: emptyList())
        }.flowOn(Dispatchers.IO)
    }

    suspend fun pokemonByType(id: String): Flow<List<Pokemon>> {
        return flow {
            val response = apiService.pokemonByType(id)
            val results = response.body()?.pokemon
            val pokemon: List<Pokemon>? = results?.map {
                it.pokemon.pokemon()
            }
            emit(pokemon ?: emptyList())
        }.flowOn(Dispatchers.IO)
    }
}

private fun Item.pokemon(): Pokemon {
    val id = url?.split("/")?.takeLast(2)?.first() ?: ""
    return Pokemon(id = id, name = name ?: "")
}

private fun PokemonResult.pokemon(): Pokemon {
    return Pokemon(
        id ?: "",
        name ?: "",
        order?.let {
            val number = when (it.length) {
                1 -> "00$it"
                2 -> "0$it"
                else -> it
            }
            "#$number"
        } ?: "",
        types?.map { Type(name = it.type?.name ?: "") } ?: emptyList(),
        height = "$height M",
        weight = "$weight KG",
        stats = emptyList(),
        imageUrl = sprites?.front_default ?: ""
    )
}

private fun Item.type(): Type {
    val id = url?.split("/")?.takeLast(2)?.first() ?: ""
    return Type(id = id, name = name ?: "")
}