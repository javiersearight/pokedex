package com.javy.pokedex.model.ui

import androidx.compose.ui.graphics.Color
import com.javy.pokedex.ui.theme.Electric
import com.javy.pokedex.ui.theme.Fire
import com.javy.pokedex.ui.theme.Grass
import com.javy.pokedex.ui.theme.Other
import com.javy.pokedex.ui.theme.Poison
import com.javy.pokedex.ui.theme.Water

data class Type(
    val id: String = "",
    val name: String = "",
    val isActive: Boolean = false
) {
    val accentColor: Color = when (name) {
        "grass" -> Grass
        "fire" -> Fire
        "water" -> Water
        "electric" -> Electric
        "poison" -> Poison
        else -> Other
    }
}