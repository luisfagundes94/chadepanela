package org.luisjulliana.bridalshower.api

import com.varabyte.kobweb.api.Api
import com.varabyte.kobweb.api.ApiContext
import com.varabyte.kobweb.api.data.getValue
import com.varabyte.kobweb.api.http.HttpMethod
import com.varabyte.kobweb.api.http.setBodyText
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import org.luisjulliana.bridalshower.database.LocalDatabase

@Api
fun list(context: ApiContext) {
    if (context.req.method != HttpMethod.GET) return
    val ownerId = context.req.params["ownerId"] ?: return

    val items = context.data.getValue<LocalDatabase>()
    context.res.setBodyText(Json.encodeToString(items[ownerId]))
    context.res.status = 200
}