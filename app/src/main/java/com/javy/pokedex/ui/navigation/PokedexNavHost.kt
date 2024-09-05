package com.javy.pokedex.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.javy.pokedex.ui.screen.PokedexScreen
import com.javy.pokedex.ui.screen.PokemonDetailScreen

object Route {
    const val POKEDEX_SCREEN = "pokedex_screen"
    const val POKEMON_DETAIL_SCREEN = "pokemon_detail_screen"
}

@Composable
fun PokedexNavHost() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = "pokedex_screen"
    ) {
        slideInOutComposable(route = Route.POKEDEX_SCREEN) {
            PokedexScreen()
        }
        slideInOutComposable(route = Route.POKEMON_DETAIL_SCREEN) {
            PokemonDetailScreen()
        }
    }
}