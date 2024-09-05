package com.javy.pokedex.data.repository

import com.javy.pokedex.data.mock.PokemonMockDataSource
import com.javy.pokedex.model.ui.Pokemon

class PokemonRepository {
    private val mockDataSource = PokemonMockDataSource()

    fun getPokemonList(): List<Pokemon> {
        return mockDataSource.getPokemonList()
    }

    fun getPokemonById(id: Int): Pokemon? {
        return mockDataSource.getPokemonById(id)
    }
}