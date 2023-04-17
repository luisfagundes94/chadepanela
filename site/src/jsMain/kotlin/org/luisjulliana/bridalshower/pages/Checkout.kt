package org.luisjulliana.bridalshower.pages

import androidx.compose.runtime.Composable
import com.luisjulliana.bridalshower.components.layouts.PageLayout
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
import com.varabyte.kobweb.core.Page
import org.jetbrains.compose.web.css.*
import org.jetbrains.compose.web.dom.Div
import org.luisjulliana.bridalshower.components.widgets.Cart
import org.luisjulliana.bridalshower.components.widgets.Form
import org.luisjulliana.bridalshower.di.KoinFactory


@Page
@Composable
fun Checkout() {
    val viewModel = KoinFactory.checkoutViewModel
    val cartItems = listOf(
        Item(
            "1",
            "Ouro",
            "https://maisretorno.com/_next/image?url=https%3A%2F%2Fmedia.maisretorno.com%2Fportal%2Fwp-content%2Fuploads%2F2022%2F02%2Fouro-envato.jpg&w=3840&q=75",
            "5777",
            "",
            categoryType = CategoryType.LIVING_ROOM,
            status = ItemStatus.AVAILABLE,
            5
        ),
        Item(
            "1",
            "Ouro",
            "https://maisretorno.com/_next/image?url=https%3A%2F%2Fmedia.maisretorno.com%2Fportal%2Fwp-content%2Fuploads%2F2022%2F02%2Fouro-envato.jpg&w=3840&q=75",
            "5777",
            "",
            categoryType = CategoryType.LIVING_ROOM,
            status = ItemStatus.AVAILABLE,
            5
        ),
    )
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
                Cart(
                    items = cartItems
                )
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
