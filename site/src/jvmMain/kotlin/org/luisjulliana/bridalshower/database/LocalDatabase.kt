package org.luisjulliana.bridalshower.database

import com.luisjulliana.bridalshower.domain.models.Item
import com.varabyte.kobweb.api.data.add
import com.varabyte.kobweb.api.init.InitApi
import com.varabyte.kobweb.api.init.InitApiContext
import java.util.UUID
import java.util.concurrent.locks.ReentrantLock
import kotlin.concurrent.withLock

@InitApi
fun initLocalDatabase(context: InitApiContext) {
    context.data.add(LocalDatabase())
}

class LocalDatabase {
    private val lock = ReentrantLock()
    private val items = mutableMapOf<String, MutableList<Item>>()

    operator fun get(ownerId: String) = lock.withLock {
        items[ownerId]
    } ?: emptyList()

    fun add(ownerId: String, item: Item) {
        lock.withLock {
            val newItem = item.copy(id = UUID.randomUUID().toString())
            items.computeIfAbsent(ownerId) {
                mutableListOf()
            }.add(newItem)
        }
    }

    fun remove(ownerId: String, id: String) {
        lock.withLock { items[ownerId]?.removeIf { it.id == id } }
    }

    fun removeAll(ownerId: String) {
        lock.withLock { items[ownerId]?.clear() }
    }
}