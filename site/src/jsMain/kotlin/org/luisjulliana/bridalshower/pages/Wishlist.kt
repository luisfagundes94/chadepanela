package org.luisjulliana.bridalshower.pages

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import com.luisjulliana.bridalshower.components.layouts.PageLayout
import com.luisjulliana.bridalshower.components.widgets.CustomCheckbox
import com.luisjulliana.bridalshower.di.KoinFactory
import com.luisjulliana.bridalshower.domain.enums.ItemStatus
import com.luisjulliana.bridalshower.domain.models.Item
import com.luisjulliana.bridalshower.extensions.openExternalLinkOnClick
import com.luisjulliana.bridalshower.styles.GridStyleVariant
import com.luisjulliana.bridalshower.styles.SubTitleStyle
import com.luisjulliana.bridalshower.styles.TitleStyle
import com.varabyte.kobweb.compose.css.FontWeight
import com.varabyte.kobweb.compose.css.TextAlign
import com.varabyte.kobweb.compose.css.outline
import com.varabyte.kobweb.compose.foundation.layout.Column
import com.varabyte.kobweb.compose.foundation.layout.Row
import com.varabyte.kobweb.compose.ui.Alignment
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.modifiers.*
import com.varabyte.kobweb.core.Page
import com.varabyte.kobweb.silk.components.layout.SimpleGrid
import com.varabyte.kobweb.silk.components.style.breakpoint.ResponsiveValues
import com.varabyte.kobweb.silk.components.style.toModifier
import com.varabyte.kobweb.silk.components.text.SpanText
import org.jetbrains.compose.web.css.*
import org.jetbrains.compose.web.dom.Div
import org.jetbrains.compose.web.dom.Img

@Page
@Composable
fun Wishlist() {
    val uiState = KoinFactory.wishlistViewModel.uiState.collectAsState()
    PageLayout("") {
        Div(
            attrs = {
                style {
                    borderRadius(10.px)
                    padding(20.px)
                    width(90.percent)
                    outline(
                        width = 2.px,
                        style = LineStyle.Solid,
                        color = rgb(235, 235, 235)
                    )
                }
            }
        ) {
            Row {
                Filters()
                Items(items = uiState.value.items)
            }
        }
    }
}

@Composable
private fun Filters() {
    Column(
        modifier = Modifier.padding(right = 50.px)
    ) {
        SpanText(
            text = "Nossa Wishlist",
            modifier = TitleStyle.toModifier().textAlign(TextAlign.Start)
        )
        SpanText(
            text = "Cômodos",
            modifier = TitleStyle.toModifier(SubTitleStyle)
                .margin(top = 20.px, bottom = 10.px)
        )
        val rooms = listOf(
            "Sala",
            "Quarto",
            "Banheiro",
            "Cozinha",
            "Outros"
        )
        CheckBoxRooms(
            checked = false,
            onCheckedChanged = {},
            rooms = rooms
        )
        SpanText(
            text = "Status",
            modifier = TitleStyle.toModifier(SubTitleStyle)
                .margin(top = 15.px, bottom = 10.px)
        )
        val status = listOf(ItemStatus.AVAILABLE, ItemStatus.TAKEN)
        CheckBoxStatus(
            status = status,
            onCheckedChanged = {},
            checked = false
        )
    }
}

@Composable
private fun Items(items: List<Item>) {
    SimpleGrid(
        numColumns = ResponsiveValues(5, 2, 4, 5, 5),
        variant = GridStyleVariant,
    ) {
        items.forEach { item ->
            Item(item)
        }
    }
}

@Composable
private fun Item(item: Item) {
    Column(
        horizontalAlignment = Alignment.Start,
        modifier = Modifier
            .width(250.px)
            .height(300.px)
            .padding(leftRight = 20.px)
            .openExternalLinkOnClick(url = item.url)
    ) {
        Img(
            src = item.imageUrl,
            alt = "Item Image",
            attrs = {
                style {
                    width(100.percent)
                    backgroundSize("contain")
                    backgroundRepeat("no-repeat")
                    backgroundPosition("center")
                }
            }
        )
        SpanText(
            text = item.name,
            modifier = Modifier
                .textAlign(TextAlign.Start)
                .margin(bottom = 10.px)
        )
        SpanText(
            text = "R$${item.price}",
            modifier = Modifier
                .fontWeight(FontWeight.SemiBold)
                .margin(bottom = 10.px)
        )
        SpanText(
            text = "Precisamos de 0/2",
            modifier = Modifier
                .fontSize(12.px)
                .color(rgb(60, 60, 60))
        )
    }
}

@Composable
fun CheckBoxRooms(
    rooms: List<String>,
    checked: Boolean = false,
    onCheckedChanged: (Boolean) -> Unit = {}
) {
    rooms.forEach { room ->
        CustomCheckbox(
            checked = checked,
            onCheckedChange = onCheckedChanged,
            label = room
        )
    }
}

@Composable
fun CheckBoxStatus(
    status: List<ItemStatus>,
    checked: Boolean = false,
    onCheckedChanged: (Boolean) -> Unit = {}
) {
    status.forEach { itemStatus ->
        CustomCheckbox(
            label = itemStatus.status,
            checked = checked,
            onCheckedChange = onCheckedChanged
        )
    }
}
