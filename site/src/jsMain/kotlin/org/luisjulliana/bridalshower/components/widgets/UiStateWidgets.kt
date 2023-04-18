package org.luisjulliana.bridalshower.components.widgets

import androidx.compose.runtime.Composable
import com.varabyte.kobweb.compose.css.TextAlign
import com.varabyte.kobweb.compose.foundation.layout.Column
import com.varabyte.kobweb.compose.ui.Alignment
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.modifiers.color
import com.varabyte.kobweb.compose.ui.modifiers.fillMaxSize
import com.varabyte.kobweb.compose.ui.modifiers.fillMaxWidth
import com.varabyte.kobweb.compose.ui.modifiers.textAlign
import com.varabyte.kobweb.silk.components.text.SpanText
import org.jetbrains.compose.web.css.Color

@Composable
fun Loading() {
    Column {
        SpanText(
            text = "Carregando...",
            modifier = Modifier.color(Color.gray)
        )
    }
}

@Composable
fun Error() {
    SpanText(
        text = "Error",
        modifier = Modifier.color(Color.gray)
    )
}

@Composable
fun Empty() {
    SpanText(
        text = "Nenhum item encontrado :(",
        modifier = Modifier
            .color(Color.gray)
            .textAlign(TextAlign.Center)
    )
}