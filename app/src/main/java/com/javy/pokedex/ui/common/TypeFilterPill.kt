package com.javy.pokedex.ui.common

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.javy.pokedex.model.ui.Type
import com.javy.pokedex.ui.theme.Black
import com.javy.pokedex.ui.theme.Blue
import com.javy.pokedex.ui.theme.DarkBlue
import com.javy.pokedex.ui.theme.White

@Composable
fun TypeFilterPill(
    type: Type,
    onTypeClick: (Type) -> Unit = {}
) {
    val backgroundColor = if (type.isActive) Blue else White
    val textColor = if (type.isActive) White else Black
    val borderColor = if (type.isActive) Black else DarkBlue

    OutlinedButton(
        border = BorderStroke(1.dp, borderColor),
        colors = ButtonDefaults.outlinedButtonColors(
            containerColor = backgroundColor,
            contentColor = textColor
        ),
        onClick = { onTypeClick(type) },
        contentPadding = PaddingValues(start = if (type.isActive) 12.dp else 16.dp, end = 16.dp),
        modifier = Modifier
            .animateContentSize()
            .height(32.dp)
            .padding(4.dp)
    ) {
        Text(text = type.name.uppercase())
    }
}

@Preview
@Composable
fun TypeFilterPillPreview() {
    TypeFilterPill(type = Type("1", "Fairy", isActive = true), onTypeClick = {})
}