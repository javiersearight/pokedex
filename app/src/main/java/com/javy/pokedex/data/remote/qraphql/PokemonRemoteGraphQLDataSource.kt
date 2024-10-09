package com.javy.pokedex.data.remote.qraphql

import com.apollographql.apollo.ApolloClient
import com.javy.pokedex.GetPokemonQuery
import com.javy.pokedex.model.ui.Pokemon
import com.javy.pokedex.model.ui.Stat
import com.javy.pokedex.model.ui.Type
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class PokemonRemoteGraphQLDataSource @Inject constructor(private val apolloClient: ApolloClient) {

    suspend fun getPokemon(): Flow<List<Pokemon>> {
        return flow {
            val response = apolloClient.query(GetPokemonQuery()).execute()
            val pokemonV2PokemonList = response.data?.pokemon_v2_pokemon
            val pokemon: List<Pokemon> = pokemonV2PokemonList?.map { it.pokemon() } ?: emptyList()
            emit(pokemon)
        }.flowOn(Dispatchers.IO)
    }


    fun GetPokemonQuery.Pokemon_v2_pokemon.pokemon(): Pokemon {
        return Pokemon(
            id = id.toString(),
            name = name,
            number = order.toString().let {
                val number = when (it.length) {
                    1 -> "00$it"
                    2 -> "0$it"
                    else -> it
                }
                "#$number"
            },
            description = "Base Experience is $base_experience",
            types = pokemon_v2_pokemontypes.map { Type(name = it.pokemon_v2_type?.name ?: "") },
            height = "${height?.toFloat()?.div(10)} M",
            weight = "${weight?.toFloat()?.div(10)} KG",
            stats = pokemon_v2_pokemonstats.map {
                Stat(
                    name = it.pokemon_v2_stat?.name ?: "",
                    value = it.base_stat
                )
            }
        )
    }
}