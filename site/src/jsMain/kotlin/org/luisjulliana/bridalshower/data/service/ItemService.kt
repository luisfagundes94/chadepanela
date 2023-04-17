package org.luisjulliana.bridalshower.data.service

import com.luisjulliana.bridalshower.domain.enums.ItemStatus
import com.varabyte.kobweb.browser.api
import kotlinx.browser.window

class ItemService {
    suspend fun fetchItems(status: ItemStatus?): ByteArray? {
        val url = if (status != null) "items?status=${status.status}"
        else "items"

        return window.api.get(url)
    }
}