package com.javy.pokedex.com.javy.pokedex.data.remote.rest.model

data class PokemonByTypeResponse(
    val pokemon: List<PokemonByTypeResult>

)

data class PokemonByTypeResult(
    val pokemon: Item

)