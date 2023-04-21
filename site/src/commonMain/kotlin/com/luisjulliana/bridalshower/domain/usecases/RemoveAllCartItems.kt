package com.luisjulliana.bridalshower.domain.usecases

import com.luisjulliana.bridalshower.domain.repositories.ItemRepository

class RemoveAllCartItems(
    private val repository: ItemRepository
) {
    suspend operator fun invoke() {
        repository.removeAllCartItems()
    }
}