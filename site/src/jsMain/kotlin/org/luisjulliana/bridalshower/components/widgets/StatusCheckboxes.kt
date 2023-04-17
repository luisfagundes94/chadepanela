import androidx.compose.runtime.Composable
import com.luisjulliana.bridalshower.domain.enums.ItemStatus
import org.luisjulliana.bridalshower.components.widgets.CustomCheckbox

@Composable
fun StatusCheckBoxes(
    onCheckChange: (ItemStatus, Boolean) -> Unit
) {
    val itemStatusList = ItemStatus.values().toList()
    itemStatusList.forEach { itemStatus ->
        CustomCheckbox(
            label = itemStatus.status,
            onCheckedChange = { isChecked ->
                onCheckChange(itemStatus, isChecked)
            }
        )
    }
}