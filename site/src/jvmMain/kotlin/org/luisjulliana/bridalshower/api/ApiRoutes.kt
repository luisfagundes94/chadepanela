package org.luisjulliana.bridalshower.api

import com.luisjulliana.bridalshower.domain.enums.ItemStatus
import com.luisjulliana.bridalshower.domain.models.Item
import com.mongodb.client.model.Filters.`in`
import com.varabyte.kobweb.api.Api
import com.varabyte.kobweb.api.ApiContext
import com.varabyte.kobweb.api.http.setBodyText
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import org.bson.conversions.Bson
import org.luisjulliana.bridalshower.database.MongoDbDatabase


@Api("/items")
suspend fun items(context: ApiContext) {
    val statusFilter = setUpStatusFilter(context)

    val bodyText = Json.encodeToString(MongoDbDatabase.getItems(statusFilter))
    context.res.setBodyText(text = bodyText)
}

private fun setUpStatusFilter(context: ApiContext): Bson {
    val statusParam = context.req.params["status"]

    return if (statusParam != null) `in`(Item::status.name, statusParam)
    else `in`(Item::status.name, ItemStatus.AVAILABLE.status, ItemStatus.TAKEN.status)
}
