package com.javy.pokedex.com.javy.pokedex.data.remote.rest.model

data class PaginatedResponse(
    val count: Int,
    val next: String?,
    val previous: String?,
    val results: List<Item>
)
