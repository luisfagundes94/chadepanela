package com.luisjulliana.bridalshower.styles

import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.modifiers.gap
import com.varabyte.kobweb.silk.components.layout.SimpleGridStyle
import org.jetbrains.compose.web.css.px

val GridStyleVariant = SimpleGridStyle.addVariant("grid") {
    base {
        Modifier.gap(5.px)
    }
}