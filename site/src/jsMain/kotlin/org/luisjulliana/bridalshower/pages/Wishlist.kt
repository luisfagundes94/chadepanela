package org.luisjulliana.bridalshower.pages

import androidx.compose.runtime.*
import com.luisjulliana.bridalshower.components.layouts.PageLayout
import com.luisjulliana.bridalshower.domain.enums.ItemStatus
import com.luisjulliana.bridalshower.domain.models.Item
import com.luisjulliana.bridalshower.styles.SubTitleStyle
import com.luisjulliana.bridalshower.styles.TitleStyle
import com.varabyte.kobweb.compose.css.*
import com.varabyte.kobweb.compose.foundation.layout.Arrangement
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
import org.jetbrains.compose.web.ExperimentalComposeWebApi
import org.jetbrains.compose.web.css.*
import org.jetbrains.compose.web.dom.Div
import org.jetbrains.compose.web.dom.Img
import org.luisjulliana.bridalshower.components.styles.GridStyleVariant
import org.luisjulliana.bridalshower.components.styles.ItemScaleAnimation
import org.luisjulliana.bridalshower.components.widgets.CustomCheckbox
import org.luisjulliana.bridalshower.components.widgets.Empty
import org.luisjulliana.bridalshower.components.widgets.Error
import org.luisjulliana.bridalshower.components.widgets.Loading
import org.luisjulliana.bridalshower.di.KoinFactory
import org.luisjulliana.bridalshower.extensions.openExternalLinkOnClick
import org.luisjulliana.bridalshower.presentation.WishlistUiState

private const val INVALID_QUANTITY = -1

@Page
@Composable
fun Wishlist() {
    val uiState by KoinFactory.wishlistViewModel.uiState.collectAsState()
    PageLayout("") {
        DivContainer {
            when {
                uiState.isLoading -> Loading()
                uiState.isEmpty -> Empty()
                uiState.items.isNotEmpty() -> Items(uiState)
                uiState.hasError -> Error()
            }
        }
    }
}

@Composable
private fun DivContainer(content: @Composable () -> Unit) {
    Div(
        attrs = {
            style {
                borderRadius(10.px)
                padding(20.px)
                minHeight(20.em)
                width(90.percent)
                outline(
                    width = 2.px,
                    style = LineStyle.Solid,
                    color = rgb(235, 235, 235)
                )
            }
        }
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
        ) {
            content.invoke()
        }
    }
}

@Composable
private fun Items(uiState: WishlistUiState) {
    Row {
        Filters()
        Items(items = uiState.items)
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
    val isItemAvailable by remember { mutableStateOf(item.status == ItemStatus.AVAILABLE) }

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
}

private fun getItemQuantityText(
    quantity: Int,
    itemStatus: ItemStatus
): String {
    return if (quantity == INVALID_QUANTITY) "A vontade"
    else if (itemStatus == ItemStatus.TAKEN) "Indisponível"
    else "Precisamos de $quantity"
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
