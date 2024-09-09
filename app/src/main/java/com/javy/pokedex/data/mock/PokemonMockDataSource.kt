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

    fun pokemonById(id: String): Pokemon? =
        when (id) {
            "1" -> bulbosaur()
            "2" -> charmander()
            "3" -> squirtle()
            "4" -> pikachu()
            "5" -> evee()
            "6" -> clefairy()
            else -> null
        }

    fun types() = listOf(
        Type("1", "fairy"),
        Type("2", "normal"),
        Type("3", "electric"),
        Type("4", "water"),
        Type("5", "fire"),
        Type("6", "grass")
    )


    private fun bulbosaur() =
        Pokemon(
            "1",
            "Bulbasaur",
            "001",
            listOf(Type("6", "grass")),
            "Bulbasaur",
            "0.7m",
            "6.9kg",
            listOf(Stat("speed", 35))
        )

    private fun charmander() =
        Pokemon(
            "2",
            "Charmander",
            "002",
            listOf(Type("5", "fire")),
            "Charmander description",
            "0.7m",
            "6.9kg",
            listOf(Stat("speed", 35)),
            "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/35.png"
        )

    private fun squirtle() =
        Pokemon(
            "3",
            "Squirtle",
            "003",
            listOf(Type("4", "water")),
            "Squirtle description",
            "0.7m",
            "6.9kg",
            listOf(Stat("speed", 35)),
            "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/35.png"
        )

    private fun pikachu() =
        Pokemon(
            "4",
            "Pikachu",
            "004",
            listOf(Type("3", "electric")),
            "Pikachu description",
            "0.7m",
            "6.9kg",
            listOf(Stat("speed", 35)),
            "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/35.png"
        )

    private fun evee() =
        Pokemon(
            "5",
            "Evee",
            "005",
            listOf(Type("2", "normal")),
            "Evee description",
            "0.7m",
            "6.9kg",
            listOf(Stat("speed", 35)),
            "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/35.png"
        )

    private fun clefairy() =
        Pokemon(
            "6",
            "Clefairy",
            "006",
            listOf(Type("1", "fairy")),
            "Clefairy description",
            "0.7m",
            "6.9kg",
            listOf(Stat("speed", 35)),
            "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/35.png"
        )
}