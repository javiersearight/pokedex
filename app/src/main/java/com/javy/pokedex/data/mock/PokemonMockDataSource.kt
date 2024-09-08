package com.javy.pokedex.data.mock

import com.javy.pokedex.model.ui.Pokemon
import com.javy.pokedex.model.ui.Stat
import com.javy.pokedex.model.ui.Type

class PokemonMockDataSource {

    fun pokemon(): List<Pokemon> =
        listOf(
            bulbosaur(), charmander(), squirtle(), pikachu(), evee(), clefairy()
        )

    fun pokemonByType(type: Type): List<Pokemon> =
        listOf(
            squirtle(), pikachu(), evee(), bulbosaur(), charmander(), squirtle(), clefairy()
        )

    fun pokemonById(id: Int): Pokemon? =
        when (id) {
            1 -> bulbosaur()
            2 -> charmander()
            3 -> squirtle()
            4 -> pikachu()
            5 -> evee()
            6 -> clefairy()
            else -> null
        }

    fun types() = listOf(
        Type(1, "Fairy"),
        Type(2, "Normal"),
        Type(3, "Electric"),
        Type(4, "Water"),
        Type(5, "Fire"),
        Type(6, "Grass")
    )


    private fun bulbosaur() =
        Pokemon(
            "1",
            "Bulbasaur",
            "001",
            listOf(Type(6, "Grass")),
            "Bulbasaur",
            "0.7m",
            "6.9kg",
            listOf(Stat("Speed", 35)),
            "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/35.png"
        )

    private fun charmander() =
        Pokemon(
            "2",
            "Charmander",
            "002",
            listOf(Type(5, "Fire")),
            "Charmander description",
            "0.7m",
            "6.9kg",
            listOf(Stat("Speed", 35)),
            "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/35.png"
        )

    private fun squirtle() =
        Pokemon(
            "3",
            "Squirtle",
            "003",
            listOf(Type(4, "Water")),
            "Squirtle description",
            "0.7m",
            "6.9kg",
            listOf(Stat("Speed", 35)),
            "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/35.png"
        )

    private fun pikachu() =
        Pokemon(
            "4",
            "Pikachu",
            "004",
            listOf(Type(3, "Electric")),
            "Pikachu description",
            "0.7m",
            "6.9kg",
            listOf(Stat("Speed", 35)),
            "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/35.png"
        )

    private fun evee() =
        Pokemon(
            "5",
            "Evee",
            "005",
            listOf(Type(2, "Normal")),
            "Evee description",
            "0.7m",
            "6.9kg",
            listOf(Stat("Speed", 35)),
            "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/35.png"
        )

    private fun clefairy() =
        Pokemon(
            "6",
            "Clefairy",
            "006",
            listOf(Type(1, "Fairy")),
            "Clefairy description",
            "0.7m",
            "6.9kg",
            listOf(Stat("Speed", 35)),
            "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/35.png"
        )
}