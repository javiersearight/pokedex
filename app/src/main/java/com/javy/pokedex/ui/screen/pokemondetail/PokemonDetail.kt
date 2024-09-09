package com.javy.pokedex.ui.screen.pokemondetail

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.javy.pokedex.ui.common.ErrorState
import com.javy.pokedex.ui.common.LoadingIndicator
import com.javy.pokedex.ui.screen.pokedex.PokemonCard
import com.javy.pokedex.ui.screen.pokedex.PokemonListEmpty

@Composable
fun PokemonDetail(
    id: String
) {
    val viewModel: PokemonDetailViewModel = hiltViewModel()
    val uiState by viewModel.uiState.collectAsState()

    LaunchedEffect(true) {
        viewModel.fetchPokemonById(id)
    }

    with(uiState) {
        if (isLoading) LoadingIndicator()

        if (hasError) ErrorState(errorMessage)

        if (isEmpty) PokemonListEmpty()

        pokemon?.let {
            Column {
                PokemonCard(pokemon = it)
                Row(modifier = Modifier.fillMaxWidth()) {
                    Column(
                        modifier = Modifier.fillMaxWidth(.5f),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = it.height,
                            modifier = Modifier.padding(start = 8.dp)
                        )
                        Text(text = "Height")
                    }
                    Column(
                        modifier = Modifier.fillMaxWidth(.5f),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = it.weight,
                            modifier = Modifier.padding(start = 8.dp)
                        )
                        Text(text = "Weight")
                    }
                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                ) {
                    Text(
                        text = "Description:",
                        modifier = Modifier.padding(start = 16.dp)
                    )
                    Text(
                        text = it.description,
                        modifier = Modifier.padding(start = 16.dp)
                    )
                }
                Text(
                    text = "Stats: ",
                    modifier = Modifier.padding(start = 24.dp)
                )
                for (stat in it.stats) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 16.dp, end = 16.dp),
                        horizontalArrangement = Arrangement.End,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(text = stat.name)
                        Text(
                            text = "${stat.value} / 300",
                            modifier = Modifier.padding(start = 16.dp, end = 16.dp)
                        )
                    }
                }
            }
        }
    }
}
