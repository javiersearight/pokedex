package com.javy.pokedex.model.ui

import androidx.compose.ui.graphics.Color
import com.javy.pokedex.ui.theme.Electric
import com.javy.pokedex.ui.theme.Fire
import com.javy.pokedex.ui.theme.Grass
import com.javy.pokedex.ui.theme.Other
import com.javy.pokedex.ui.theme.Poison
import com.javy.pokedex.ui.theme.Water

data class Pokemon(
    val id: String,
    val name: String,
    val number: String = "",
    val types: List<Type> = emptyList(),
    val description: String = "",
    val height: String = "",
    val weight: String = "",
    val stats: List<Stat> = emptyList(),
    val imageUrl: String? = null
) {
    val accentColor: Color =
        if (types.isNotEmpty()) {
            when (types[0].name) {
                "grass" -> Grass
                "fire" -> Fire
                "water" -> Water
                "electric" -> Electric
                "poison" -> Poison
                else -> Other
            }
        } else Other
}