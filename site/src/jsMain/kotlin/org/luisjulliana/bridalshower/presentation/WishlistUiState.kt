package org.luisjulliana.bridalshower.presentation

import com.luisjulliana.bridalshower.domain.models.Item

data class WishlistUiState(
    val items: List<Item> = emptyList(),
    val isLoading: Boolean = false,
    val isEmpty: Boolean = false,
    val hasError: Boolean = false
)