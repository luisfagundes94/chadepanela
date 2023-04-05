package com.luisjulliana.bridalshower.domain.models

import com.luisjulliana.bridalshower.domain.enums.RoomType
import com.luisjulliana.bridalshower.domain.enums.ItemStatus
import kotlinx.serialization.Serializable

@Serializable
data class Item(
    val id: String,
    val name: String,
    val imageUrl: String,
    val price: String,
    val url: String,
    val roomType: RoomType,
    val status: ItemStatus,
    val quantity: Int
)
