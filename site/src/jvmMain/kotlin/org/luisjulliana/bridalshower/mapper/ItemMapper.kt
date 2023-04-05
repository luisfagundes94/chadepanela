package org.luisjulliana.bridalshower.mapper

import com.luisjulliana.bridalshower.domain.enums.ItemStatus
import com.luisjulliana.bridalshower.domain.enums.RoomType
import com.luisjulliana.bridalshower.domain.models.Item
import org.bson.Document

private const val INVALID_QUANTITY = -1

object ItemMapper {
    fun List<Document>.mapToDomain() = map { document ->
        Item(
            id = document.getObjectId("_id").toString(),
            name = document.getString("name"),
            imageUrl = document.getString("imageUrl"),
            price = document.getString("price"),
            url = document.getString("url"),
            roomType = RoomType.fromName(document.getString("roomType")),
            status = ItemStatus.fromName(document.getString("status")),
            quantity = getQuantity(document.getString("quantity"))
        )
    }
}
private fun getQuantity(quantity: String?): Int {
    return if (quantity.isNullOrBlank()) INVALID_QUANTITY else quantity.toInt()
}

