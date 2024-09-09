package com.javy.pokedex.com.javy.pokedex.data.remote.rest.model

import com.squareup.moshi.Json

class StatResult {
    @Json(name = "base_stat")
    val baseStat: Int? = null
    val effort: Int? = null
    val stat: Item? = null
}