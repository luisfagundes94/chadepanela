package org.luisjulliana.bridalshower.components.widgets

import androidx.compose.runtime.Composable
import com.varabyte.kobweb.compose.css.TextAlign
import com.varabyte.kobweb.compose.foundation.layout.Row
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.modifiers.margin
import com.varabyte.kobweb.compose.ui.modifiers.textAlign
import com.varabyte.kobweb.compose.ui.styleModifier
import com.varabyte.kobweb.silk.components.text.SpanText
import org.jetbrains.compose.web.attributes.InputType
import org.jetbrains.compose.web.css.*
import org.jetbrains.compose.web.dom.Input

const val ROW_BOTTOM_MARGIN = 5
const val SMALL_MARGIN = 5

@Composable
fun CustomCheckbox(
    modifier: Modifier = Modifier,
    onCheckedChange: (Boolean) -> Unit,
    label: String
) {
    Row(
        modifier = modifier
            .styleModifier {
                display(DisplayStyle.Flex)
                alignItems(AlignItems.Start)
            }
            .margin(bottom = ROW_BOTTOM_MARGIN.px)

    ) {
        Input(
            type = InputType.Checkbox,
            attrs = {
                onChange {
                    val newCheckedValue = !it.value
                    onCheckedChange(newCheckedValue)
                }
            }
        )
        SpanText(
            text = label,
            modifier = Modifier
                .textAlign(TextAlign.Start)
                .margin(left = SMALL_MARGIN.px)
        )
    }
}