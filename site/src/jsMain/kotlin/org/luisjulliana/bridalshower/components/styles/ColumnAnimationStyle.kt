package org.luisjulliana.bridalshower.components.styles

import com.varabyte.kobweb.compose.css.CSSTransition
import com.varabyte.kobweb.compose.css.transition
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.styleModifier
import com.varabyte.kobweb.silk.components.style.ComponentStyle
import com.varabyte.kobweb.silk.components.style.hover
import org.jetbrains.compose.web.ExperimentalComposeWebApi
import org.jetbrains.compose.web.css.AnimationTimingFunction
import org.jetbrains.compose.web.css.s
import org.jetbrains.compose.web.css.transform


@OptIn(ExperimentalComposeWebApi::class)
val ItemScaleAnimation by ComponentStyle {
    base {
        Modifier.styleModifier {
            transition(
                CSSTransition(
                    property = "transform",
                    duration = 0.25.s,
                    timingFunction = AnimationTimingFunction.EaseInOut
                )
            )
            transform {
                scale(1.0)
            }
        }
    }
    hover {
        Modifier.styleModifier {
            transition(
                CSSTransition(
                    property = "transform",
                    duration = 0.25.s,
                    timingFunction = AnimationTimingFunction.EaseInOut
                )
            )
            transform {
                scale(1.2)
            }
        }
    }
}