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
import org.jetbrains.compose.web.css.em
import org.jetbrains.compose.web.css.px
import org.luisjulliana.bridalshower.utils.black

val DefaultFontStyle by ComponentStyle {
    default {
        Modifier.fontFamily("Roboto")
    }
}

val LinkStyle by ComponentStyle {
    base {
        Modifier
            .textDecorationLine(TextDecorationLine.None)
            .fontSize(1.25.em)
    }

    anyLink {
        Modifier.color(black)
    }

    hover {
        Modifier.color(Color.gray)
    }
}

val TitleStyle by ComponentStyle {
    base {
        Modifier
            .color(black)
            .fontSize(20.px)
            .fontWeight(FontWeight.Bold)
    }
}

val LargeTitleVariant = TitleStyle.addVariant("largeTitle") {
    base {
        Modifier.fontSize(30.px)
    }
}

val SubTitleStyle = TitleStyle.addVariant("subTitle") {
    base {
        Modifier.fontSize(15.px)
    }
}