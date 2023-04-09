package com.luisjulliana.bridalshower.domain.usecases

import com.luisjulliana.bridalshower.domain.enums.ItemStatus
import com.luisjulliana.bridalshower.domain.repositories.ItemRepository

class GetItems(private val repository: ItemRepository) {
    suspend operator fun invoke(itemStatus: ItemStatus?) = repository.getItems(itemStatus)
}