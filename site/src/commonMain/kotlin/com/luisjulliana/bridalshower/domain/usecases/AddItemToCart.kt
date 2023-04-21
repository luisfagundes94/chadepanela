package com.luisjulliana.bridalshower.domain.usecases

import com.luisjulliana.bridalshower.domain.models.Item
import com.luisjulliana.bridalshower.domain.repositories.ItemRepository

class AddItemToCart(private val repository: ItemRepository) {
    suspend operator fun invoke(item: Item) {
        repository.addCartItem(item)
    }
}
