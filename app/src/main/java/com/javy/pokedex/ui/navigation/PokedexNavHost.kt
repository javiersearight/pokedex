package com.javy.pokedex.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.javy.pokedex.ui.navigation.Route.POKEDEX_SCREEN
import com.javy.pokedex.ui.navigation.Route.POKEMON_DETAIL_SCREEN
import com.javy.pokedex.ui.screen.pokedex.PokedexScreen
import com.javy.pokedex.ui.screen.pokemondetail.PokemonDetailScreen

object Route {
    const val POKEDEX_SCREEN = "pokedex"
    const val POKEMON_DETAIL_SCREEN = "pokemon/{id}"
}

@Composable
fun PokedexNavHost() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = POKEDEX_SCREEN
    ) {
        slideInOutComposable(route = POKEDEX_SCREEN) {
            PokedexScreen(
                onPokemonSelected = {
                    navController.navigate(
                        POKEMON_DETAIL_SCREEN.replace(
                            oldValue = "{id}",
                            newValue = it.id
                        )
                    )
                }
            )
        }
        slideInOutComposable(route = POKEMON_DETAIL_SCREEN) {
            val id = it.arguments?.getString("id") ?: ""
            PokemonDetailScreen(
                id = id,
                navigateBack = {
                    navController.navigateUp()
                }
            )
        }
    }
}