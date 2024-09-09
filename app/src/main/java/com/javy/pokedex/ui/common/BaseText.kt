package com.javy.pokedex.ui.common

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.javy.pokedex.ui.theme.White


@Composable
fun BaseText(text: String) {
    Text(
        text = text,
        modifier = Modifier.padding(16.dp, 4.dp, 16.dp, 4.dp),
        color = White
    )
}