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

}