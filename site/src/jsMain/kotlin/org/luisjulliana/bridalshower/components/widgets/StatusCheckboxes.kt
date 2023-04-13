import androidx.compose.runtime.Composable
import com.luisjulliana.bridalshower.domain.enums.ItemStatus
import org.luisjulliana.bridalshower.components.widgets.CustomCheckbox
import org.luisjulliana.bridalshower.presentation.wishlist.WishlistViewModel

@Composable
fun StatusCheckBoxes(
    viewModel: WishlistViewModel
) {
    listOf(
        ItemStatus.AVAILABLE,
        ItemStatus.TAKEN
    ).forEach { itemStatus ->
        CustomCheckbox(
            label = itemStatus.status,
            onCheckedChange = { isChecked ->
                if (isChecked) viewModel.fetchItems(itemStatus = itemStatus)
                else viewModel.fetchItems()
            }
        )
    }
}