import androidx.compose.runtime.Composable
import com.luisjulliana.bridalshower.domain.enums.ItemStatus
import org.luisjulliana.bridalshower.components.widgets.CustomRadioButton

@Composable
fun StatusCheckBoxes(
    onCheckChange: (ItemStatus, Boolean) -> Unit
) {
    val itemStatusList = ItemStatus.values().toList()
    itemStatusList.forEach { itemStatus ->
        CustomRadioButton(
            label = itemStatus.status,
            onCheckChange = { isChecked ->
                onCheckChange(itemStatus, isChecked)
            }
        )
    }
}