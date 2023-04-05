package org.luisjulliana.bridalshower.components.animations

import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.modifiers.scale
import com.varabyte.kobweb.silk.components.animation.Keyframes

val scaleSize = Keyframes("scaleSize") {
    from { Modifier.scale(1f) }
    to { Modifier.scale(1.1f) }
}