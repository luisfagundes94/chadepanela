package org.luisjulliana.bridalshower.pages

import StatusCheckBoxes
import androidx.compose.runtime.*
import com.luisjulliana.bridalshower.components.layouts.PageLayout
import com.luisjulliana.bridalshower.domain.enums.CategoryType
import com.luisjulliana.bridalshower.domain.enums.ItemStatus
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
import org.luisjulliana.bridalshower.components.styles.GridStyleVariant
import org.luisjulliana.bridalshower.components.widgets.*
import org.luisjulliana.bridalshower.di.KoinFactory
import org.luisjulliana.bridalshower.presentation.wishlist.WishlistViewModel


@Page
@Composable
fun Wishlist() {
    val viewModel = remember { KoinFactory.wishlistViewModel }
    val uiState by viewModel.uiState.collectAsState()

    PageLayout("") {
        DivContainer {
            when {
                uiState.isLoading -> Loading()
                uiState.isEmpty -> Empty()
                uiState.items.isNotEmpty() -> Items(
                    items = uiState.items,
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
    items: List<Item>,
    viewModel: WishlistViewModel
) {
    Row {
        Filters(
            onStatusCheck = { itemStatus, isChecked ->
                viewModel.fetchItems(
                    itemStatus = if (isChecked) itemStatus else null
                )
            },
            onCategoryCheck = { categoryType ->
                viewModel.fetchItems(
                    categoryType = categoryType
                )
            }
        )
        Items(
            items = items
        )
    }
}

@Composable
private fun Filters(
    onStatusCheck: (ItemStatus, Boolean) -> Unit,
    onCategoryCheck: (CategoryType) -> Unit
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
        CheckBoxCategories(
            onCheckChange = { roomType ->
                onCategoryCheck(roomType)
            },
            categories = CategoryType.values().toList()
        )
        SpanText(
            text = "Status",
            modifier = TitleStyle.toModifier(SubTitleStyle)
                .margin(top = 15.px, bottom = 10.px)
        )
        StatusCheckBoxes { itemStatus, isChecked ->
            onStatusCheck(itemStatus, isChecked)
        }
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
fun CheckBoxCategories(
    categories: List<CategoryType>,
    onCheckChange: (CategoryType) -> Unit
) {
    var selectedRoomType by remember { mutableStateOf(CategoryType.ALL) }

    categories.forEach { room ->
        CustomRadioButton(
            label = room.type,
            isChecked = selectedRoomType == room,
            onCheckChange = { isChecked ->
                selectedRoomType = if (isChecked) room else CategoryType.ALL
                onCheckChange(selectedRoomType)
            }
        )
    }
}




