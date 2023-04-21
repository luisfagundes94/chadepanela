package com.luisjulliana.bridalshower.domain.usecases

import com.luisjulliana.bridalshower.domain.repositories.ItemRepository

class ListCartItems(
    private val repository: ItemRepository
) {
    suspend operator fun invoke() = repository.listCartItems()
}