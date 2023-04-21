package org.luisjulliana.bridalshower.data.service

import com.luisjulliana.bridalshower.domain.enums.CategoryType
import com.luisjulliana.bridalshower.domain.enums.ItemStatus
import com.varabyte.kobweb.browser.api
import kotlinx.browser.window

class ItemService {
    suspend fun fetchItems(
        status: ItemStatus?,
        categoryType: CategoryType?
    ): ByteArray? {
        val queryParams = mutableListOf<String>()

        status?.let {
            queryParams.add("status=${it.status}")
        }

        categoryType?.let {
            queryParams.add("categoria=${it.type}")
        }

        val url = buildString {
            append("items")

            if (queryParams.isNotEmpty()) {
                append("?")
                append(queryParams.joinToString(separator = "&"))
            }
        }
        return window.api.get(url)
    }

    suspend fun listItemsFromLocalDatabase() = window.api.get("list?ownerId=${fetchOwnerId()}")

    suspend fun addItemToLocalDatabase(item: ByteArray) = window.api.post(
        apiPath = "addItem?ownerId=${fetchOwnerId()}",
        body = item
    )

    suspend fun removeAllItemsFromLocalDatabase() = window.api.delete(
        apiPath = "removeAllItems?ownerId=${fetchOwnerId()}"
    )

    suspend fun removeItemFromLocalDatabase(itemId: String) = window.api.delete(
        apiPath = "remove?ownerId=${fetchOwnerId()}&itemId=$itemId"
    )

    private suspend fun fetchOwnerId() = window.localStorage.getItem("id") ?: run {
        window.api.get("id")!!.decodeToString().also {
            window.localStorage.setItem("id", it)
        }
    }
}