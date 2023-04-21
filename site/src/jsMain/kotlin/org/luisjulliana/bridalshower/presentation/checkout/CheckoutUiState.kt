package org.luisjulliana.bridalshower.presentation.checkout

import com.luisjulliana.bridalshower.domain.models.Item

data class CheckoutUiState(
    val items: List<Item> = emptyList(),
    val isEmpty: Boolean = false,
    val hasError: Boolean = false,
)
