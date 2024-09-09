package com.javy.pokedex.ui.screen.pokedex

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.CachePolicy
import coil.request.ImageRequest
import com.javy.pokedex.R
import com.javy.pokedex.model.ui.Pokemon
import com.javy.pokedex.model.ui.Stat
import com.javy.pokedex.model.ui.Type
import com.javy.pokedex.ui.common.BaseText
import com.javy.pokedex.ui.common.TypeFilterPill

@Composable
fun PokemonCard(
    pokemon: Pokemon,
    onPokemonClick: (Pokemon) -> Unit = {},
) {
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
                val modifier = Modifier
                    .defaultMinSize(minHeight = 150.dp)
                    .fillMaxWidth()
                if (pokemon.imageUrl == null) {
                    Image(
                        painter = painterResource(id = R.drawable.logo_pokemon),
                        contentDescription = "pokemon logo placeholder",
                        modifier = modifier
                    )
                } else {
                    val context = LocalContext.current
                    val request = ImageRequest.Builder(context)
                        .data(pokemon.imageUrl)
                        .placeholder(R.drawable.logo_pokemon)
                        .memoryCacheKey(pokemon.imageUrl)
                        .diskCachePolicy(CachePolicy.ENABLED)
                        .memoryCachePolicy(CachePolicy.ENABLED)
                        .build()
                    AsyncImage(
                        model = request,
                        contentDescription = pokemon.name,
                        contentScale = ContentScale.Fit,
                        modifier = modifier
                    )
                }
                BaseText(text = pokemon.name.uppercase())
                BaseText(text = pokemon.number)
                Row {
                    for (type in pokemon.types) {
                        TypeFilterPill(type = type, enableAccent = true)
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
        listOf(Type("6", "Grass")),
        "Bulbasaur",
        "0.7m",
        "6.9kg",
        listOf(Stat("Speed", 35)),
        "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/35.png"
    )
    PokemonCard(pokemon = pokemon)
}