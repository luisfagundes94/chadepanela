package org.luisjulliana.bridalshower.presentation.wishlist

import com.luisjulliana.bridalshower.core.DataState
import com.luisjulliana.bridalshower.domain.enums.CategoryType
import com.luisjulliana.bridalshower.domain.enums.ItemStatus
import com.luisjulliana.bridalshower.domain.models.Item
import com.luisjulliana.bridalshower.domain.usecases.AddItemToCart
import com.luisjulliana.bridalshower.domain.usecases.GetItems
import com.luisjulliana.bridalshower.domain.usecases.RemoveCartItem
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class WishlistViewModel(
    private val getItems: GetItems,
    private val addItem: AddItemToCart,
    private val removeItem: RemoveCartItem,
    private val coroutineScope: CoroutineScope = CoroutineScope(Dispatchers.Main),
) {

    private val _uiState = MutableStateFlow(WishlistUiState(isLoading = true))
    val uiState: StateFlow<WishlistUiState> = _uiState.asStateFlow()

    fun fetchItemsFromRemoteApi(
        itemStatus: ItemStatus? = null,
        categoryType: CategoryType? = null
    ) {
        coroutineScope.launch {
            handleResult(
                getItems(
                    itemStatus, categoryType
                )
            )
        }
    }

    fun addItemToCart(item: Item) = coroutineScope.launch {
        addItem(item)
    }

    fun removeCartItem(id: String) = coroutineScope.launch {
        removeItem(id)
    }

    private fun handleResult(data: DataState<List<Item>>) {
        when (data) {
            is DataState.Success -> {
                _uiState.update { state ->
                    state.copy(
                        items = data.result,
                        isLoading = false,
                        hasError = false,
                        isEmpty = false
                    )
                }
            }

            is DataState.Error -> {
                _uiState.update { state ->
                    state.copy(
                        items = emptyList(),
                        isLoading = false,
                        hasError = true,
                        isEmpty = false
                    )
                }
            }

            is DataState.Empty -> {
                _uiState.update { state ->
                    state.copy(
                        items = emptyList(),
                        isLoading = false,
                        hasError = false,
                        isEmpty = true
                    )
                }
            }
        }
    }
}