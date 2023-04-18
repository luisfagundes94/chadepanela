package org.luisjulliana.bridalshower.components.widgets

import com.varabyte.kobweb.compose.css.Cursor
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.modifiers.backgroundColor
import com.varabyte.kobweb.compose.ui.modifiers.borderRadius
import com.varabyte.kobweb.compose.ui.modifiers.cursor
import com.varabyte.kobweb.silk.components.style.ComponentStyle
import org.jetbrains.compose.web.css.px
import org.jetbrains.compose.web.css.rgb
import org.luisjulliana.bridalshower.utils.darkGreen
import org.luisjulliana.bridalshower.utils.green

val DefaultButtonStyle by ComponentStyle {
    base {
        Modifier
            .backgroundColor(green)
            .borderRadius(5.px)
            .cursor(Cursor.Pointer)
    }

    cssRule(".silk-button_light:hover:not([aria-disabled=\"true\"])") {
       Modifier
           .backgroundColor(darkGreen)
    }
}