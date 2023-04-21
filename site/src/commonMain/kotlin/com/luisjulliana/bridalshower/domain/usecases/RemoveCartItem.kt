package com.luisjulliana.bridalshower.domain.usecases

import com.luisjulliana.bridalshower.domain.repositories.ItemRepository

class RemoveCartItem(
    private val repository: ItemRepository
) {
    suspend operator fun invoke(id: String) {
        repository.removeCartItem(id)
    }
}