package com.javy.pokedex.ui.screen

import androidx.compose.runtime.Composable
import androidx.compose.material3.Text
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun PokemonDetailScreen(id: String) {
    // placeholder text
    Text("Pokemon Detail Screen: $id")
}

@Preview
@Composable
fun PokemonDetailScreenPreview() {
    PokemonDetailScreen("")
}