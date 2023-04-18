package com.luisjulliana.bridalshower.domain.usecases

import com.luisjulliana.bridalshower.domain.enums.CategoryType
import com.luisjulliana.bridalshower.domain.enums.ItemStatus
import com.luisjulliana.bridalshower.domain.repositories.ItemRepository

class GetItems(private val repository: ItemRepository) {
    suspend operator fun invoke(
        itemStatus: ItemStatus?,
        categoryType: CategoryType?
    ) = repository.getItems(
        status = itemStatus,
        categoryType = if (categoryType == CategoryType.ALL) null else categoryType
    )
}