package com.luisjulliana.bridalshower.domain.repositories

import com.luisjulliana.bridalshower.core.DataState
import com.luisjulliana.bridalshower.domain.enums.ItemStatus
import com.luisjulliana.bridalshower.domain.models.Item

interface ItemRepository {
    suspend fun getItems(status: ItemStatus? = null): DataState<List<Item>>
    suspend fun removeItem(id: Int)
    suspend fun addItem(item: Item)
    suspend fun updateItem(item: Item)
}