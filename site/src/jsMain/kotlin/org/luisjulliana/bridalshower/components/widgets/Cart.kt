package org.luisjulliana.bridalshower.components.widgets

import androidx.compose.runtime.Composable
import com.luisjulliana.bridalshower.domain.models.Item
import com.varabyte.kobweb.compose.css.*
import com.varabyte.kobweb.compose.foundation.layout.Arrangement
import com.varabyte.kobweb.compose.foundation.layout.Column
import com.varabyte.kobweb.compose.foundation.layout.Row
import com.varabyte.kobweb.compose.ui.Alignment
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.modifiers.color
import com.varabyte.kobweb.compose.ui.modifiers.fillMaxWidth
import com.varabyte.kobweb.compose.ui.modifiers.fontWeight
import com.varabyte.kobweb.compose.ui.modifiers.margin
import com.varabyte.kobweb.silk.components.style.toModifier
import com.varabyte.kobweb.silk.components.text.SpanText
import org.jetbrains.compose.web.ExperimentalComposeWebApi
import org.jetbrains.compose.web.css.*
import org.jetbrains.compose.web.dom.Div
import org.jetbrains.compose.web.dom.Img
import org.luisjulliana.bridalshower.components.layouts.HorizontalSpacer
import org.luisjulliana.bridalshower.components.layouts.LineDivider
import org.luisjulliana.bridalshower.components.layouts.VerticalSpacer
import org.luisjulliana.bridalshower.components.styles.SubTitleStyle
import org.luisjulliana.bridalshower.components.styles.TitleStyle
import org.luisjulliana.bridalshower.utils.SMALL_SPACER
import org.luisjulliana.bridalshower.utils.VERY_SMALL_SPACER


@Composable
fun Cart(items: List<Item>) {
    val totalPrice = items.sumOf { it.price.toInt() }

    Column(
        modifier = Modifier
            .margin(leftRight = VERY_SMALL_SPACER.px),
        horizontalAlignment = Alignment.Start,
    ) {
        SpanText(
            text = "Seus Items",
            modifier = TitleStyle.toModifier()
        )
        LineDivider()
        VerticalSpacer(height = SMALL_SPACER)
        items.forEach { item ->
            CartItem(item = item)
        }
        VerticalSpacer(height = SMALL_SPACER)
        SpanText(
            text = "Total $${totalPrice}",
            modifier = TitleStyle.toModifier(SubTitleStyle)
        )
    }
}

@OptIn(ExperimentalComposeWebApi::class)
@Composable
private fun CartItem(item: Item) {
    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        Div(
            attrs = {
                style {
                    borderRadius(10.px)
                    width(10.em)
                    height(5.em)
                    position(Position.Relative)
                    overflow(Overflow.Hidden)
                }
            }
        ) {
            Img(
                src = item.imageUrl,
                alt = item.name,
                attrs = {
                    style {
                        width(100.percent)
                        height(100.percent)
                        objectFit(ObjectFit.Cover)
                        position(Position.Absolute)
                        top(50.percent)
                        left(50.percent)
                        transform {
                            translate((-50).percent, (-50).percent)
                        }
                    }
                }
            )
        }
        HorizontalSpacer(width = 20)
        Column(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.Center
        ) {
            SpanText(
                modifier = TitleStyle.toModifier(SubTitleStyle),
                text = item.name
            )
            VerticalSpacer(height = 10)
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                SpanText(
                    modifier = TitleStyle.toModifier(SubTitleStyle),
                    text = "R$ ${item.price}"
                )
                SpanText(
                    modifier = Modifier
                        .color(rgb(150,150,150))
                        .fontWeight(FontWeight.Bold),
                    text = " x${item.quantity}"
                )
            }
        }
    }
    LineDivider()
}
