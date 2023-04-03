package org.luisjulliana.bridalshower.api

import com.varabyte.kobweb.api.Api
import com.varabyte.kobweb.api.ApiContext
import com.varabyte.kobweb.api.http.setBodyText
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import org.luisjulliana.bridalshower.database.MongoDbDatabase


@Api("/items")
suspend fun items(context: ApiContext) {
    val bodyText = Json.encodeToString(MongoDbDatabase.getItems())
    context.res.setBodyText(text = bodyText)
}
