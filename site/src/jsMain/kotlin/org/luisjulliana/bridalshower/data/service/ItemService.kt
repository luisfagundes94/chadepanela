package org.luisjulliana.bridalshower.data.service

import com.varabyte.kobweb.browser.api
import kotlinx.browser.window

class ItemService {
    suspend fun fetchItems() = window.api.get("items")
}