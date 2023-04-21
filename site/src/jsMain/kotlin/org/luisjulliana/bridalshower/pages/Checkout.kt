package org.luisjulliana.bridalshower.pages

import androidx.compose.runtime.*
import org.luisjulliana.bridalshower.components.layouts.PageLayout
import com.luisjulliana.bridalshower.domain.enums.CategoryType
import com.luisjulliana.bridalshower.domain.enums.ItemStatus
import com.luisjulliana.bridalshower.domain.models.Item
import com.varabyte.kobweb.compose.css.boxShadow
import com.varabyte.kobweb.compose.foundation.layout.Arrangement
import com.varabyte.kobweb.compose.foundation.layout.Column
import com.varabyte.kobweb.compose.foundation.layout.Row
import com.varabyte.kobweb.compose.ui.Alignment
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.modifiers.fillMaxSize
import com.varabyte.kobweb.compose.ui.modifiers.fillMaxWidth
import com.varabyte.kobweb.compose.ui.modifiers.padding
import com.varabyte.kobweb.core.Page
import com.varabyte.kobweb.silk.components.text.SpanText
import org.jetbrains.compose.web.css.*
import org.jetbrains.compose.web.dom.Div
import org.luisjulliana.bridalshower.components.widgets.Cart
import org.luisjulliana.bridalshower.components.widgets.Form
import org.luisjulliana.bridalshower.di.KoinFactory


@Page
@Composable
fun Checkout() {
    val viewModel = remember { KoinFactory.checkoutViewModel }
    val isCartEmpty = remember { mutableStateOf(false) }
    val uiState by viewModel.uiState.collectAsState()

    LaunchedEffect(true, isCartEmpty.value) {
        viewModel.listCartItems()
    }

    PageLayout("") {
        Row(
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxWidth(50.percent),
        ) {
            DivContainer {
                Form(
                    viewModel = viewModel
                )
            }
            DivContainer {
                when {
                    uiState.hasError -> SpanText(
                        text = "Erro",
                        modifier = Modifier.padding(5.em)
                    )

                    uiState.isEmpty -> SpanText(
                        text = "Carrinho vazio",
                        modifier = Modifier.padding(5.em)
                    )

                    uiState.items.isNotEmpty() -> Cart(
                        items = uiState.items,
                        onEmptyCartClick = {
                            viewModel.emptyCart()
                            isCartEmpty.value = true
                        }
                    )
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
                margin(20.px)
                borderRadius(10.px)
                padding(20.px)
                boxShadow(
                    offsetX = 0.px,
                    offsetY = 0.px,
                    blurRadius = 5.px,
                    spreadRadius = 0.px,
                    color = rgb(200, 200, 200)
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
