package com.javy.pokedex.data.remote.rest.model

import com.javy.pokedex.com.javy.pokedex.data.remote.rest.model.Sprites
import com.javy.pokedex.com.javy.pokedex.data.remote.rest.model.StatResult
import com.javy.pokedex.com.javy.pokedex.data.remote.rest.model.TypeResult

data class PokemonResult(
    val id: String?,
    val name: String?,
    val order: String?,
    val types: List<TypeResult>?,
    val height: Int?,
    val weight: Int?,
    val sprites: Sprites?,
    val stats: List<StatResult>?,
    val base_experience: Int?
)