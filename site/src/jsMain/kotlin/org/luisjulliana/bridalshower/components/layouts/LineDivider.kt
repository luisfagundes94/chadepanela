package org.luisjulliana.bridalshower.components.layouts

import androidx.compose.runtime.Composable
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.modifiers.borderColor
import com.varabyte.kobweb.compose.ui.modifiers.fillMaxWidth
import com.varabyte.kobweb.silk.components.layout.Divider
import org.jetbrains.compose.web.css.rgb

@Composable
fun LineDivider() = Divider(
    modifier = Modifier
        .fillMaxWidth()
        .borderColor(rgb(240, 240, 240))
)