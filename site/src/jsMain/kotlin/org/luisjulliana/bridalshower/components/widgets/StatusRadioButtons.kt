import androidx.compose.runtime.*
import com.luisjulliana.bridalshower.domain.enums.ItemStatus
import org.luisjulliana.bridalshower.components.widgets.CustomRadioButton

@Composable
fun StatusRadioButtons(
    onCheckChange: (ItemStatus) -> Unit,
    statusList: List<ItemStatus>
) {
    var selectedStatus by remember { mutableStateOf(ItemStatus.ALL) }

    statusList.forEach { itemStatus ->
        CustomRadioButton(
            label = itemStatus.status,
            isChecked = selectedStatus == itemStatus,
            onCheckChange = { isChecked ->
                selectedStatus = if (isChecked) itemStatus else ItemStatus.ALL
                onCheckChange(itemStatus)
            }
        )
    }
}