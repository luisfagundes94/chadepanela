package org.luisjulliana.bridalshower.pages

import androidx.compose.runtime.Composable
import org.luisjulliana.bridalshower.components.layouts.PageLayout
import com.varabyte.kobweb.compose.css.backdropFilter
import com.varabyte.kobweb.compose.css.functions.blur
import com.varabyte.kobweb.compose.css.functions.brightness
import com.varabyte.kobweb.compose.foundation.layout.Arrangement
import com.varabyte.kobweb.compose.foundation.layout.Column
import com.varabyte.kobweb.compose.ui.Alignment
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.modifiers.*
import com.varabyte.kobweb.compose.ui.styleModifier
import com.varabyte.kobweb.core.Page
import com.varabyte.kobweb.core.rememberPageContext
import com.varabyte.kobweb.silk.components.forms.Button
import com.varabyte.kobweb.silk.components.style.toModifier
import com.varabyte.kobweb.silk.components.text.SpanText
import org.jetbrains.compose.web.css.*
import org.jetbrains.compose.web.dom.Div
import org.luisjulliana.bridalshower.components.layouts.VerticalSpacer
import org.luisjulliana.bridalshower.components.styles.LargeTitleVariant
import org.luisjulliana.bridalshower.components.styles.TitleStyle
import org.luisjulliana.bridalshower.components.widgets.CountdownTimer
import org.luisjulliana.bridalshower.components.widgets.DefaultButtonStyle
import org.luisjulliana.bridalshower.utils.DEFAULT_SPACER
import org.luisjulliana.bridalshower.utils.LARGE_SPACER
import org.luisjulliana.bridalshower.utils.SMALL_SPACER

private const val IMAGE_URL =
    "url('https://images.pexels.com/photos/958545/pexels-photo-958545.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=2')"
private const val BUTTON_MESSAGE = "Escolher items"

@Page
@Composable
fun Home() {
    PageLayout("") {
        WelcomeBackdrop()
        Countdown()
        AboutUs()
    }
}

@Composable
private fun WelcomeBackdrop() {
    val context = rememberPageContext()
    Div(
        attrs = {
            style {
                width(100.percent)
                height(30.em)
                position(Position.Relative)
                backgroundImage(IMAGE_URL)
                backgroundSize("cover")
                backgroundRepeat("no-repeat")
                backgroundPosition("bottom")
            }
        }
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxSize()
                .styleModifier {
                    width(100.percent)
                    height(100.percent)
                    position(Position.Absolute)
                    backdropFilter(brightness(0.5), blur(2.px))
                }
        ) {
            SpanText(
                text = "Venha participar do nosso chá de panela!",
                modifier = TitleStyle.toModifier(LargeTitleVariant)
                    .color(Color.whitesmoke)
            )
            VerticalSpacer(height = DEFAULT_SPACER)
            Button(
                modifier = DefaultButtonStyle.toModifier(),
                onClick = {
                    context.router.navigateTo("wishlist")
                }
            ) {
                SpanText(
                    text = BUTTON_MESSAGE
                )
            }
        }

    }
}

@Composable
private fun Countdown() {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxWidth()
            .height(30.em)
    ) {
        SpanText(
            text = "Contagem regressiva para o Chá de Panela",
            modifier = TitleStyle.toModifier(LargeTitleVariant)
        )
        VerticalSpacer(height = DEFAULT_SPACER)
        CountdownTimer()
    }
}

@Composable
private fun AboutUs() {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top,
        modifier = Modifier
            .fillMaxWidth()
            .height(30.em)
            .padding(leftRight = 10.percent)
    ) {
        SpanText(
            text = "SEJAM BEM-VINDOS AO NOSSO SITE!",
            modifier = TitleStyle.toModifier(LargeTitleVariant)
        )
        VerticalSpacer(height = SMALL_SPACER)
        SpanText(
            text = "A melhor forma de compartilhar esse momento com vocês é vivendo juntos esse sonho!"
        )
        VerticalSpacer(height = LARGE_SPACER)
        SpanText(
            text = "---------------------------------------"
        )
        VerticalSpacer(height = LARGE_SPACER)
        SpanText(
            text = "Aqui vamos contar à vocês, queridos amigos e familiares, os momentos mais marcantes da nossa história de amor. A contagem regressiva começa, o frio na barriga e toda a ansiedade do dia mais esperado de nossas vidas nos enche de alegria em tê-los ao nosso lado. Vamos juntos nesse grande sonho, o dia em que uniremos nossas almas e corpos para sempre, o dia do nosso casamento."
        )
    }
}
