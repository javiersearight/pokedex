package com.javy.pokedex.ui.screen.pokedex

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.javy.pokedex.model.ui.Pokemon
import com.javy.pokedex.model.ui.Stat
import com.javy.pokedex.model.ui.Type
import com.javy.pokedex.ui.common.BaseText
import com.javy.pokedex.ui.common.TypeFilterPill

@Composable
fun PokemonCard(
    pokemon: Pokemon,
    onPokemonClick: (Pokemon) -> Unit = {}) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        onClick = { onPokemonClick(pokemon) }
    ) {
        Box(
            modifier = Modifier
                .background(pokemon.accentColor)
                .fillMaxWidth()
        ) {
            Column {
                AsyncImage(
                    model = pokemon.imageUrl,
                    contentDescription = pokemon.name,
                    contentScale = ContentScale.Fit,
                    modifier = Modifier
                        .height(150.dp)
                        .fillMaxWidth()
                )
                BaseText(text = pokemon.name)
                BaseText(text = pokemon.number)
                Row {
                    for (type in pokemon.types) {
                        TypeFilterPill(type)
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun PokemonCardPreview() {
    val pokemon = Pokemon(
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
    PokemonCard(pokemon = pokemon)
}