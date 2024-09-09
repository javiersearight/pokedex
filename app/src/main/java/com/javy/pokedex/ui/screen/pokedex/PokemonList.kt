package com.javy.pokedex.ui.screen.pokedex

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.hilt.navigation.compose.hiltViewModel
import com.javy.pokedex.model.ui.Pokemon
import com.javy.pokedex.ui.common.ErrorState
import com.javy.pokedex.ui.common.LoadingIndicator
import com.javy.pokedex.ui.common.TypeFilterPill

@Composable
fun PokemonList(
    onPokemonSelected: (Pokemon) -> Unit
) {
    val viewModel: PokemonListViewModel = hiltViewModel()
    val uiState by viewModel.uiState.collectAsState()

    with(uiState) {
        if (isLoading) LoadingIndicator()

        if (hasError) ErrorState(errorMessage)

        if (isEmpty) PokemonListEmpty()

        Column {
            LazyRow(
                horizontalArrangement = Arrangement.Start,
                verticalAlignment = Alignment.CenterVertically
            ) {
                items(types.size) { index ->
                    TypeFilterPill(
                        type = types[index],
                        onTypeClick = { viewModel.updateTypeFilter(it) }
                    )
                }
            }

            LazyVerticalGrid(columns = GridCells.Fixed(2)) {
                items(pokemon.size) {
                    PokemonCard(
                        pokemon = pokemon[it],
                        onPokemonClick = { selected -> onPokemonSelected(selected) })
                }
            }
        }
    }
}