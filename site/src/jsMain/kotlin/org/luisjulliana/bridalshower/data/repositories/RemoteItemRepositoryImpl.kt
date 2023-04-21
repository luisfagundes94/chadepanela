package org.luisjulliana.bridalshower.data.repositories

import com.luisjulliana.bridalshower.core.DataState
import com.luisjulliana.bridalshower.domain.enums.CategoryType
import com.luisjulliana.bridalshower.domain.enums.ItemStatus
import com.luisjulliana.bridalshower.domain.models.Item
import com.luisjulliana.bridalshower.domain.repositories.ItemRepository
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import org.luisjulliana.bridalshower.data.service.ItemService

class RemoteItemRepositoryImpl(
    private val itemService: ItemService
) : ItemRepository {
    override suspend fun getItems(
        status: ItemStatus?,
        categoryType: CategoryType?
    ): DataState<List<Item>> {
        val jsonString = itemService.fetchItems(
            status = status,
            categoryType = categoryType
        )?.decodeToString()

        return try {
            val result: List<Item>? = jsonString?.let { Json.decodeFromString(it) }
            if (result.isNullOrEmpty()) DataState.Empty else DataState.Success(result)
        } catch (exception: Exception) {
            DataState.Error(exception)
        }
    }

    override suspend fun removeCartItem(id: String) {
        itemService.removeItemFromLocalDatabase(itemId = id)
    }

    override suspend fun removeAllCartItems() {
        itemService.removeAllItemsFromLocalDatabase()
    }

    override suspend fun listCartItems(): DataState<List<Item>> {
        val jsonString = itemService.listItemsFromLocalDatabase()?.decodeToString()

        return try {
            val result: List<Item>? = jsonString?.let { Json.decodeFromString(it) }
            if (result.isNullOrEmpty()) DataState.Empty else DataState.Success(result)
        } catch (exception: Exception) {
            DataState.Error(exception)
        }
    }

    override suspend fun addCartItem(item: Item) {
        val jsonString = Json.encodeToString(item)
        itemService.addItemToLocalDatabase(jsonString.encodeToByteArray())
    }

    override suspend fun updateItem(item: Item) {
        TODO("Not yet implemented")
    }
}