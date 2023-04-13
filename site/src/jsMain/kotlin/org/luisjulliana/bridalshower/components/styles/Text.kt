package org.luisjulliana.bridalshower.components.styles

import com.varabyte.kobweb.compose.css.FontWeight
import com.varabyte.kobweb.compose.css.TextDecorationLine
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.modifiers.*
import com.varabyte.kobweb.silk.components.style.ComponentStyle
import com.varabyte.kobweb.silk.components.style.anyLink
import com.varabyte.kobweb.silk.components.style.default
import com.varabyte.kobweb.silk.components.style.hover
import org.jetbrains.compose.web.css.Color
import org.jetbrains.compose.web.css.px
import org.jetbrains.compose.web.css.rgb

val DefaultFontStyle by ComponentStyle {
    default {
        Modifier.fontFamily("Roboto")
    }
}

val LinkStyle by ComponentStyle {
    base {
        Modifier.textDecorationLine(TextDecorationLine.None)
    }

    anyLink {
        Modifier.color(rgb(25, 25, 25))
    }

    hover {
        Modifier.color(Color.gray)
    }
}

val TitleStyle by ComponentStyle {
    base {
        Modifier
            .color(rgb(8,33,0))
            .fontSize(20.px)
            .fontWeight(FontWeight.Bold)
    }
}

val SubTitleStyle = TitleStyle.addVariant("subTitle") {
    base {
        Modifier.fontSize(15.px)
    }
}