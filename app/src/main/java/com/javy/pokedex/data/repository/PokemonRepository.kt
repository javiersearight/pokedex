package com.javy.pokedex.data.repository

import com.javy.pokedex.data.mock.PokemonMockDataSource
import com.javy.pokedex.model.ui.Pokemon
import com.javy.pokedex.model.ui.Type

class PokemonRepository {
    private val mockDataSource = PokemonMockDataSource()

    fun pokemon(): List<Pokemon> =
        mockDataSource.pokemon()


    fun pokemonById(id: String): Pokemon? =
        mockDataSource.pokemonById(id)


    fun types(): List<Type> =
        mockDataSource.types()

    fun pokemonByType(type: Type): List<Pokemon> =
        mockDataSource.pokemonByType(type)
}