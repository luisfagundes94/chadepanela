package org.luisjulliana.bridalshower.components.widgets

import androidx.compose.runtime.*
import com.varabyte.kobweb.compose.foundation.layout.Arrangement
import com.varabyte.kobweb.compose.foundation.layout.Column
import com.varabyte.kobweb.compose.ui.Alignment
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.modifiers.color
import com.varabyte.kobweb.compose.ui.modifiers.fillMaxWidth
import com.varabyte.kobweb.silk.components.forms.Button
import com.varabyte.kobweb.silk.components.style.toModifier
import com.varabyte.kobweb.silk.components.text.SpanText
import org.jetbrains.compose.web.attributes.InputType
import org.jetbrains.compose.web.attributes.placeholder
import org.jetbrains.compose.web.attributes.required
import org.jetbrains.compose.web.css.*
import org.jetbrains.compose.web.dom.Input
import org.luisjulliana.bridalshower.components.layouts.VerticalSpacer
import org.luisjulliana.bridalshower.presentation.checkout.CheckoutViewModel
import org.luisjulliana.bridalshower.utils.DEFAULT_SPACER
import org.luisjulliana.bridalshower.utils.SMALL_SPACER

private const val VALID_INFO_MESSAGE = "Obrigado por confirmar sua presença!"
private const val INVALID_INFO_MESSAGE = "Preencha todos os campos!"
private const val BUTTON_MESSAGE = "Confirmar Escolha e Presença"

@Composable
fun Form(
    viewModel: CheckoutViewModel
) {
    var userName by remember { mutableStateOf("") }
    var userEmail by remember { mutableStateOf("") }
    val isInfoValid = viewModel.checkUserInfo(userName, userEmail)
    var confirmationMessage by remember { mutableStateOf(INVALID_INFO_MESSAGE) }

    Column(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Input(
            type = InputType.Text,
            attrs = {
                placeholder("Nome")
                required()
                onInput { userName = it.value }
                style { inputTextStyle() }
            }
        )
        VerticalSpacer(height = SMALL_SPACER)
        Input(
            type = InputType.Email,
            attrs = {
                placeholder("E-mail")
                required()
                onInput { userEmail = it.value }
                style { inputTextStyle() }
            }
        )
        VerticalSpacer(height = DEFAULT_SPACER)
        Button(
            modifier = DefaultButtonStyle.toModifier(),
            onClick = {
                confirmationMessage = VALID_INFO_MESSAGE
                viewModel.sendEmailToUser(userEmail, userName)
            },
            enabled = isInfoValid
        ) {
            SpanText(
                modifier = Modifier.color(rgb(8, 33, 0)),
                text = BUTTON_MESSAGE,
            )
        }
        VerticalSpacer(height = SMALL_SPACER)
        SpanText(
            text = confirmationMessage,
            modifier = Modifier.color(Color.lightgray)
        )
    }
}

private fun StyleScope.inputTextStyle() {
    border(
        width = 1.px,
        style = LineStyle.Solid,
        color = rgb(220, 220, 220)
    )
    width(25.em)
    padding(10.px)
}

