package com.javy.pokedex.data.remote.rest

import com.javy.pokedex.com.javy.pokedex.data.remote.rest.model.PaginatedResponse
import com.javy.pokedex.com.javy.pokedex.data.remote.rest.model.PokemonByTypeResponse
import com.javy.pokedex.data.remote.rest.model.PokemonResult
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface PokemonApiService {

    @GET("pokemon")
    suspend fun pokemon(): Response<PaginatedResponse>

    @GET("pokemon/{id}")
    suspend fun pokemonById(@Path("id") id: String): Response<PokemonResult>

    @GET("type")
    suspend fun types(): Response<PaginatedResponse>

    @GET("type/{id}")
    suspend fun pokemonByType(@Path("id") id: String): Response<PokemonByTypeResponse>
}