package org.luisjulliana.bridalshower.mapper

import com.luisjulliana.bridalshower.domain.enums.CategoryType
import com.luisjulliana.bridalshower.domain.enums.ItemStatus
import com.luisjulliana.bridalshower.domain.models.Item
import org.bson.Document

private const val INVALID_QUANTITY = -1

object ItemMapper {
    fun List<Document>.mapToDomain() = map { document ->
        Item(
            id = document.getObjectId("_id").toString(),
            name = document.getString("nome"),
            imageUrl = document.getString("imagem"),
            price = document.getString("preço").trim(),
            url = document.getString("site"),
            categoryType = CategoryType.fromName(document.getString("categoria")),
            status = ItemStatus.getFromName(document.getString("status")),
            quantity = getQuantity(document.getString("quantidade").trim())
        )
    }
}
private fun getQuantity(quantity: String?) =
    if (quantity.isNullOrBlank()) INVALID_QUANTITY
    else quantity.toInt()

