package org.luisjulliana.bridalshower.pages

import StatusCheckBoxes
import androidx.compose.runtime.*
import com.luisjulliana.bridalshower.components.layouts.PageLayout
import com.luisjulliana.bridalshower.domain.enums.RoomType
import com.luisjulliana.bridalshower.domain.models.Item
import org.luisjulliana.bridalshower.components.styles.SubTitleStyle
import org.luisjulliana.bridalshower.components.styles.TitleStyle
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
import org.jetbrains.compose.web.css.*
import org.jetbrains.compose.web.dom.Div
import org.luisjulliana.bridalshower.components.widgets.WishlistItem
import org.luisjulliana.bridalshower.components.styles.GridStyleVariant
import org.luisjulliana.bridalshower.components.widgets.CustomCheckbox
import org.luisjulliana.bridalshower.components.widgets.Empty
import org.luisjulliana.bridalshower.components.widgets.Error
import org.luisjulliana.bridalshower.components.widgets.Loading
import org.luisjulliana.bridalshower.di.KoinFactory
import org.luisjulliana.bridalshower.presentation.wishlist.WishlistUiState
import org.luisjulliana.bridalshower.presentation.wishlist.WishlistViewModel



@Page
@Composable
fun Wishlist() {
    val viewModel = KoinFactory.wishlistViewModel
    val uiState by viewModel.uiState.collectAsState()

    PageLayout("") {
        DivContainer {
            when {
                uiState.isLoading -> Loading()
                uiState.isEmpty -> Empty()
                uiState.items.isNotEmpty() -> Items(
                    uiState = uiState,
                    viewModel = viewModel
                )

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
                marginBottom(20.px)
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
private fun Items(
    uiState: WishlistUiState,
    viewModel: WishlistViewModel
) {
    Row {
        Filters(
            viewModel = viewModel
        )
        Items(
            items = uiState.items
        )
    }
}

@Composable
private fun Filters(
    viewModel: WishlistViewModel
) {
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
        CheckBoxRooms(
            onCheckedChanged = {},
            rooms = RoomType.values().map { it.type }
        )
        SpanText(
            text = "Status",
            modifier = TitleStyle.toModifier(SubTitleStyle)
                .margin(top = 15.px, bottom = 10.px)
        )
        StatusCheckBoxes(
            viewModel = viewModel
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
            WishlistItem(item)
        }
    }
}

@Composable
fun CheckBoxRooms(
    rooms: List<String>,
    onCheckedChanged: (Boolean) -> Unit = {}
) {
    rooms.forEach { room ->
        CustomCheckbox(
            onCheckedChange = onCheckedChanged,
            label = room
        )
    }
}


