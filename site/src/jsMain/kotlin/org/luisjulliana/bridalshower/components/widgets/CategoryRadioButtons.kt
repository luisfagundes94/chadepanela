package org.luisjulliana.bridalshower.components.widgets

import androidx.compose.runtime.*
import com.luisjulliana.bridalshower.domain.enums.CategoryType

@Composable
fun CategoryRadioButtons(
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