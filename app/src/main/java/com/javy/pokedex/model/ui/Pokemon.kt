package com.javy.pokedex.model.ui

data class Pokemon(
    val id: Int,
    val name: String,
    val number: String,
    val type: List<Type>,
    val description: String,
    val height: String,
    val weight: String,
    val stats: List<Stat>
)