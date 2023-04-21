package org.luisjulliana.bridalshower.api

import com.luisjulliana.bridalshower.domain.models.Item
import com.varabyte.kobweb.api.Api
import com.varabyte.kobweb.api.ApiContext
import com.varabyte.kobweb.api.data.getValue
import com.varabyte.kobweb.api.http.HttpMethod
import com.varabyte.kobweb.api.http.setBodyText
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import org.luisjulliana.bridalshower.database.LocalDatabase

@Api("addItem")
fun addItem(context: ApiContext) {
    if (context.req.method != HttpMethod.POST) return

    val ownerId = context.req.params["ownerId"] ?: return
    val bodyByteArray = context.req.body ?: return
    val bodyText = bodyByteArray.decodeToString()
    val item = Json.decodeFromString<Item>(bodyText)

    context.data.getValue<LocalDatabase>().add(ownerId, item)
    context.res.status = 200
}