package org.luisjulliana.bridalshower.presentation

import com.luisjulliana.bridalshower.domain.usecases.GetItems
import com.luisjulliana.bridalshower.presentation.WishlistUiState
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

    private fun fetchItems() {
        coroutineScope.launch {
            getItems().let { items ->
                _uiState.value = WishlistUiState(items = items)
            }
        }
    }
}