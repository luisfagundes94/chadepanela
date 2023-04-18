package org.luisjulliana.bridalshower.components.widgets

import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import com.luisjulliana.bridalshower.domain.enums.ItemStatus
import com.luisjulliana.bridalshower.domain.models.Item
import com.varabyte.kobweb.compose.css.*
import com.varabyte.kobweb.compose.foundation.layout.Arrangement
import com.varabyte.kobweb.compose.foundation.layout.Column
import com.varabyte.kobweb.compose.foundation.layout.Row
import com.varabyte.kobweb.compose.foundation.layout.Spacer
import com.varabyte.kobweb.compose.ui.Alignment
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.modifiers.*
import com.varabyte.kobweb.silk.components.icons.fa.FaCartPlus
import com.varabyte.kobweb.silk.components.icons.fa.FaCirclePlus
import com.varabyte.kobweb.silk.components.icons.fa.FaPlus
import com.varabyte.kobweb.silk.components.icons.fa.IconSize
import com.varabyte.kobweb.silk.components.style.toModifier
import com.varabyte.kobweb.silk.components.text.SpanText
import org.jetbrains.compose.web.ExperimentalComposeWebApi
import org.jetbrains.compose.web.css.*
import org.jetbrains.compose.web.dom.Div
import org.jetbrains.compose.web.dom.Img
import org.luisjulliana.bridalshower.components.styles.ItemScaleAnimation
import org.luisjulliana.bridalshower.extensions.openExternalLinkOnClick

private const val INVALID_QUANTITY = -1

@Composable
fun WishlistItem(item: Item) {
    val isItemAvailable by remember(item.status) {
        derivedStateOf {
            ItemStatus.AVAILABLE.status == item.status.status
        }
    }
    Column(
        horizontalAlignment = Alignment.Start,
        modifier = ItemScaleAnimation.toModifier()
            .backgroundColor(Color.white)
            .opacity(if (isItemAvailable) 1f else .5f)
            .width(250.px)
            .padding(bottom = 15.px)
            .borderRadius(topLeft = 10.px, bottomRight = 10.px)
            .boxShadow(
                offsetX = 0.px,
                offsetY = 0.px,
                blurRadius = 5.px,
                spreadRadius = 0.px,
                color = rgb(200, 200, 200)
            )
            .openExternalLinkOnClick(
                url = item.url,
                isItemAvailable = isItemAvailable
            )

    ) {
        ItemImage(
            imageUrl = item.imageUrl
        )
        SpanText(
            text = item.name,
            modifier = Modifier
                .textAlign(TextAlign.Start)
                .margin(topBottom = .3.em)
                .padding(leftRight = 15.px)
        )
        Row(
            horizontalArrangement = Arrangement.Start,
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(
                modifier = Modifier
            ) {
                SpanText(
                    text = "R$${item.price}",
                    modifier = Modifier
                        .fontWeight(FontWeight.SemiBold)
                        .padding(leftRight = 15.px)
                )
                SpanText(
                    text = getItemQuantityText(
                        quantity = item.quantity,
                        itemStatus = item.status
                    ),
                    modifier = Modifier
                        .fontSize(12.px)
                        .color(rgb(60, 60, 60))
                        .padding(leftRight = 15.px)
                )
            }
            Spacer()
            FaCirclePlus(
                size = IconSize.LG,
                modifier = Modifier
                    .align(Alignment.CenterVertically)
                    .align(Alignment.End)
                    .padding(leftRight = 15.px)
                    .onClick {

                    }
            )
        }
    }
}

private fun getItemQuantityText(
    quantity: Int,
    itemStatus: ItemStatus
): String {
    return when {
        quantity == INVALID_QUANTITY -> "A vontade"
        itemStatus == ItemStatus.TAKEN -> ItemStatus.TAKEN.status
        itemStatus == ItemStatus.AVAILABLE -> "Precisamos de $quantity"
        else -> "Precisamos de $quantity"
    }
}


@OptIn(ExperimentalComposeWebApi::class)
@Composable
private fun ItemImage(imageUrl: String) {
    Div(
        attrs = {
            style {
                borderRadius(
                    topLeft = 10.px,
                    topRight = 10.px,
                    bottomRight = 0.px,
                    bottomLeft = 0.px
                )
                width(100.percent)
                height(200.px)
                position(Position.Relative)
                overflow(Overflow.Hidden)
            }
        }
    ) {
        Img(
            src = imageUrl,
            alt = "Item Image",
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
}