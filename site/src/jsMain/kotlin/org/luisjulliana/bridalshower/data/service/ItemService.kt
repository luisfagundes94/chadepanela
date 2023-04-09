package org.luisjulliana.bridalshower.data.service

import com.luisjulliana.bridalshower.domain.enums.ItemStatus
import com.varabyte.kobweb.browser.api
import kotlinx.browser.window

class ItemService {
    suspend fun fetchItems(status: ItemStatus?): ByteArray? {
        status?.let {
            return window.api.get("/items?status=${status.status}")
        }
        return window.api.get("/items")
    }
}