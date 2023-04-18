package org.luisjulliana.bridalshower.components.widgets

import androidx.compose.runtime.*
import com.varabyte.kobweb.compose.foundation.layout.Column
import com.varabyte.kobweb.compose.foundation.layout.Row
import com.varabyte.kobweb.compose.ui.Alignment
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.modifiers.*
import com.varabyte.kobweb.silk.components.style.toModifier
import com.varabyte.kobweb.silk.components.text.SpanText
import kotlinx.coroutines.delay
import kotlinx.datetime.Clock
import kotlinx.datetime.Instant
import org.jetbrains.compose.web.css.Color
import org.jetbrains.compose.web.css.em
import org.luisjulliana.bridalshower.components.layouts.VerticalSpacer
import org.luisjulliana.bridalshower.components.styles.SubTitleStyle
import org.luisjulliana.bridalshower.components.styles.TitleStyle
import org.luisjulliana.bridalshower.utils.SMALL_SPACER
import org.luisjulliana.bridalshower.utils.black
import org.luisjulliana.bridalshower.utils.green

private const val BRIDAL_SHOWER_DATE = "2023-06-17T14:00:00Z"


@Composable
fun CountdownTimer(
    targetTime: Instant = Instant.parse(BRIDAL_SHOWER_DATE)
) {
    val currentTime = remember { mutableStateOf(Clock.System.now()) }
    var remainingTime by remember { mutableStateOf(0) }
    var days by remember { mutableStateOf(0) }
    var hours by remember { mutableStateOf(0) }
    var minutes by remember { mutableStateOf(0) }
    var sec by remember { mutableStateOf(0) }

    LaunchedEffect(targetTime) {
        while (remainingTime >= 0) {
            currentTime.value = Clock.System.now()
            remainingTime = ((targetTime - currentTime.value).inWholeSeconds).toInt()
            days = remainingTime / (24 * 60 * 60)
            hours = (remainingTime % (24 * 60 * 60)) / (60 * 60)
            minutes = (remainingTime % (60 * 60)) / 60
            sec = remainingTime % 60

            delay(1000)
        }

        println("Countdown has finished!")
    }

    Row {
        Item(days, "dias")
        Item(hours, "horas")
        Item(minutes, "minutos")
        Item(sec, "segundos")
    }
}

@Composable
private fun Item(
    value: Int,
    text: String
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.padding(2.em)
    ) {
        SpanText(
            text = value.toString(),
            modifier = Modifier
                .fontSize(5.em)
                .backgroundColor(green)
                .color(black)
                .borderRadius(0.5.em)
                .padding(0.3.em)
        )
        VerticalSpacer(height = SMALL_SPACER)
        SpanText(
            text = text,
            modifier = Modifier.fontSize(1.25.em)
        )
    }
}