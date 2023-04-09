package org.luisjulliana.bridalshower.presentation

import com.luisjulliana.bridalshower.core.DataState
import com.luisjulliana.bridalshower.domain.enums.ItemStatus
import com.luisjulliana.bridalshower.domain.models.Item
import com.luisjulliana.bridalshower.domain.usecases.GetItems
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class WishlistViewModel(
    private val getItems: GetItems
) {
    private val coroutineScope = CoroutineScope(Dispatchers.Main)

    private val _uiState = MutableStateFlow(WishlistUiState(isLoading = true))
    val uiState: StateFlow<WishlistUiState> = _uiState.asStateFlow()

    init {
        fetchItems()
    }

    fun fetchItems(itemStatus: ItemStatus? = null) {
        coroutineScope.launch {
            handleResult(getItems(itemStatus))
        }
    }

    private fun handleResult(data: DataState<List<Item>>) {
        when (data) {
            is DataState.Success -> {
                _uiState.value = WishlistUiState(items = data.result)
            }
            is DataState.Error -> {
                _uiState.value = WishlistUiState(hasError = true)
            }
            is DataState.Empty -> {
                _uiState.value = WishlistUiState(isEmpty = true)
            }
        }
    }
}