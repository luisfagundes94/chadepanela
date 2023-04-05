package org.luisjulliana.bridalshower.extensions

import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.attrsModifier
import kotlinx.browser.window

fun Modifier.openExternalLinkOnClick(
    url: String,
    target: String = "_blank"
) =
    attrsModifier {
        onClick {
            if (url.isBlank()) return@onClick
            window.open(url, target)
        }
    }