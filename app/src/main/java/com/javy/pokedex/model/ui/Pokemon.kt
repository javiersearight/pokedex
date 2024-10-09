package com.javy.pokedex.model.ui

import androidx.compose.ui.graphics.Color
import com.javy.pokedex.ui.theme.Other

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
            types[0].accentColor
        } else Other

    val hasCompleteModel: Boolean
        get() = number.isNotEmpty()
}