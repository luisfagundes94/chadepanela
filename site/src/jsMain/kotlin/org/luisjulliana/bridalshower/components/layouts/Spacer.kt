package org.luisjulliana.bridalshower.components.layouts

import androidx.compose.runtime.Composable
import org.jetbrains.compose.web.css.*
import org.jetbrains.compose.web.dom.Div

@Composable
fun VerticalSpacer(height: Int) {
    Div({
        style {
            this.height(height.px)
        }
    })
}

@Composable
fun HorizontalSpacer(width: Int) {
    Div({
        style {
            this.width(width.px)
        }
    })
}

@Composable
fun HorizontalSpacer(width: CSSSizeValue<CSSUnit.percent>) {
    Div({
        style {
            this.width(width)
        }
    })
}
