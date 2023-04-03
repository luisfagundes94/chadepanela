package org.luisjulliana.bridalshower.data.repositories

import com.luisjulliana.bridalshower.domain.models.Item
import com.luisjulliana.bridalshower.domain.repositories.ItemRepository
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import org.luisjulliana.bridalshower.data.service.ItemService

class RemoteItemRepositoryImpl(
    private val itemService: ItemService
): ItemRepository {
    override suspend fun getItems(): List<Item> {
        val jsonString = itemService.fetchItems()?.decodeToString()
        return jsonString?.let { Json.decodeFromString(it) } ?: emptyList()
    }

    override suspend fun removeItem(id: Int) {
        TODO("Not yet implemented")
    }

    override suspend fun addItem(item: Item) {
        TODO("Not yet implemented")
    }

    override suspend fun updateItem(item: Item) {
        TODO("Not yet implemented")
    }

}