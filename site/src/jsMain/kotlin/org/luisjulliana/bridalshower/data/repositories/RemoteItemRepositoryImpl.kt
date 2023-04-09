package org.luisjulliana.bridalshower.data.repositories

import com.luisjulliana.bridalshower.core.DataState
import com.luisjulliana.bridalshower.domain.enums.ItemStatus
import com.luisjulliana.bridalshower.domain.models.Item
import com.luisjulliana.bridalshower.domain.repositories.ItemRepository
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import org.luisjulliana.bridalshower.data.service.ItemService

class RemoteItemRepositoryImpl(
    private val itemService: ItemService
): ItemRepository {
    override suspend fun getItems(status: ItemStatus?): DataState<List<Item>> {
        val jsonString = itemService.fetchItems(status)?.decodeToString()

        return try {
            val result: List<Item>? = jsonString?.let { Json.decodeFromString(it) }
            if (result.isNullOrEmpty()) DataState.Empty else DataState.Success(result)
        } catch (exception: Exception) {
            DataState.Error(exception)
        }
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