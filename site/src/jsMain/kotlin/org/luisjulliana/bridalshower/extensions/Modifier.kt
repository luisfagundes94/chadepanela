package org.luisjulliana.bridalshower.extensions

import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.attrsModifier
import kotlinx.browser.window

fun Modifier.openExternalLinkOnClick(
    url: String,
    isItemAvailable: Boolean = true,
    target: String = "_blank"
) =
    attrsModifier {
        onClick {
            if (url.isBlank() or !isItemAvailable) return@onClick
            window.open(url, target)
        }
    }