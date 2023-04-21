package org.luisjulliana.bridalshower.pages

import StatusRadioButtons
import androidx.compose.runtime.*
import org.luisjulliana.bridalshower.components.layouts.PageLayout
import com.luisjulliana.bridalshower.domain.enums.CategoryType
import com.luisjulliana.bridalshower.domain.enums.ItemStatus
import com.luisjulliana.bridalshower.domain.models.Item
import org.luisjulliana.bridalshower.components.styles.SubTitleStyle
import org.luisjulliana.bridalshower.components.styles.TitleStyle
import com.varabyte.kobweb.compose.css.*
import com.varabyte.kobweb.compose.foundation.layout.Arrangement
import com.varabyte.kobweb.compose.foundation.layout.Box
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
import org.luisjulliana.bridalshower.components.layouts.HorizontalSpacer
import org.luisjulliana.bridalshower.components.styles.GridStyleVariant
import org.luisjulliana.bridalshower.components.widgets.*
import org.luisjulliana.bridalshower.di.KoinFactory
import org.luisjulliana.bridalshower.utils.DEFAULT_SPACER


@Page
@Composable
fun Wishlist() {
    val viewModel = remember { KoinFactory.wishlistViewModel }
    val uiState by viewModel.uiState.collectAsState()

    val statusFilter = remember { mutableStateOf<ItemStatus?>(null) }
    val categoryFilter = remember { mutableStateOf<CategoryType?>(null) }

    LaunchedEffect(statusFilter.value, categoryFilter.value) {
        viewModel.fetchItemsFromRemoteApi(
            itemStatus = statusFilter.value,
            categoryType = categoryFilter.value
        )
    }

    PageLayout("") {
        DivContainer {
            Row(
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier.fillMaxSize(),
            ) {
                Filters(
                    onStatusCheck = { itemStatus ->
                        statusFilter.value = itemStatus
                    },
                    onCategoryCheck = { categoryType ->
                        categoryFilter.value = categoryType
                    }
                )
                HorizontalSpacer(width = DEFAULT_SPACER)
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier.fillMaxSize()
                ) {
                    when {
                        uiState.isLoading -> Loading()
                        uiState.isEmpty -> Empty()
                        uiState.items.isNotEmpty() -> Items(
                            items = uiState.items,
                            onAddItemToCart = { item ->
                                viewModel.addItemToCart(item)
                            },
                            onRemoveItemFromCart = { itemId ->
                                viewModel.removeCartItem(itemId)
                            }
                        )
                        uiState.hasError -> Error()
                    }
                }
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
private fun Filters(
    onStatusCheck: (ItemStatus) -> Unit,
    onCategoryCheck: (CategoryType) -> Unit
) {
    Column(
        modifier = Modifier.minWidth(Width.FitContent),
        horizontalAlignment = Alignment.Start
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
        CategoryRadioButtons(
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
        StatusRadioButtons(
            onCheckChange = { onStatusCheck(it) },
            statusList = ItemStatus.values().toList()
        )
    }
}

@Composable
private fun Items(
    items: List<Item>,
    onAddItemToCart: (Item) -> Unit,
    onRemoveItemFromCart: (String) -> Unit,
) {
    SimpleGrid(
        numColumns = ResponsiveValues(5, 2, 4, 5, 5),
        variant = GridStyleVariant,
    ) {
        items.forEach { item ->
            WishlistItem(
                item = item,
                onAddItemToCart = onAddItemToCart,
                onRemoveItemFromCart = onRemoveItemFromCart
            )
        }
    }
}