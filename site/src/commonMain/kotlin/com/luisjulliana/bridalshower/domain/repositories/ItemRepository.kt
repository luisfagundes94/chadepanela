package com.luisjulliana.bridalshower.domain.repositories

import com.luisjulliana.bridalshower.core.DataState
import com.luisjulliana.bridalshower.domain.enums.CategoryType
import com.luisjulliana.bridalshower.domain.enums.ItemStatus
import com.luisjulliana.bridalshower.domain.models.Item

interface ItemRepository {
    suspend fun getItems(
        status: ItemStatus?,
        categoryType: CategoryType?
    ): DataState<List<Item>>
    suspend fun removeCartItem(id: String)
    suspend fun removeAllCartItems()
    suspend fun listCartItems(): DataState<List<Item>>
    suspend fun addCartItem(item: Item)
    suspend fun updateItem(item: Item)
}