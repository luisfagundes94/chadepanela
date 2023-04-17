package org.luisjulliana.bridalshower.components.sections

import androidx.compose.runtime.Composable
import com.varabyte.kobweb.compose.foundation.layout.Arrangement
import com.varabyte.kobweb.compose.foundation.layout.Column
import com.varabyte.kobweb.compose.foundation.layout.Row
import com.varabyte.kobweb.compose.ui.Alignment
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.modifiers.fillMaxSize
import com.varabyte.kobweb.compose.ui.modifiers.fontFamily
import com.varabyte.kobweb.compose.ui.modifiers.fontSize
import com.varabyte.kobweb.compose.ui.modifiers.margin
import com.varabyte.kobweb.compose.ui.styleModifier
import com.varabyte.kobweb.silk.components.navigation.Link
import com.varabyte.kobweb.silk.components.navigation.UndecoratedLinkVariant
import com.varabyte.kobweb.silk.components.style.toModifier
import com.varabyte.kobweb.silk.components.text.SpanText
import org.jetbrains.compose.web.css.*
import org.luisjulliana.bridalshower.components.styles.LinkStyle

private const val HEADER_TITLE_SIZE = 60
private const val HEADER_TOP_MARGIN = 60
private const val SUB_HEADER_TITLE_SIZE = 20
private const val NAV_LINK_HORIZONTAL_MARGIN = 15
private const val NAV_HEADER_BACKGROUND_IMAGE_URL = "url('https://i.ibb.co/31Q2yrN/plant-Background01.png')"

@Composable
fun NavHeader() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .styleModifier {
                width(100.percent)
                height(400.px)
                backgroundImage(NAV_HEADER_BACKGROUND_IMAGE_URL)
                backgroundSize("cover")
                backgroundRepeat("no-repeat")
                backgroundPosition("bottom")
            },
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        SpanText(
            text = "Chá de panela",
            modifier = Modifier
                .fontFamily("Handlee")
                .fontSize(HEADER_TITLE_SIZE.px)
                .margin(top = HEADER_TOP_MARGIN.px)
        )
        SpanText(
            text = "Luís e Julliana",
            modifier = Modifier
                .fontFamily("Handlee")
                .fontSize(SUB_HEADER_TITLE_SIZE.px)
        )
    }
    Row(
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.Bottom
    ) {
        NavLink("/", "HOME")
        NavLink("/wishlist", "WISHLIST")
        NavLink("/checkout", "CARRINHO")
    }
}

@Composable
private fun NavLink(
    path: String,
    text: String
) {
    Link(
        path = path,
        text = text,
        variant = UndecoratedLinkVariant,
        modifier = LinkStyle.toModifier()
            .margin(
                leftRight = NAV_LINK_HORIZONTAL_MARGIN.px
            )
    )
}

