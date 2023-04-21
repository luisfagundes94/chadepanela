package org.luisjulliana.bridalshower.api

import com.varabyte.kobweb.api.Api
import com.varabyte.kobweb.api.ApiContext
import com.varabyte.kobweb.api.data.getValue
import com.varabyte.kobweb.api.http.HttpMethod
import org.luisjulliana.bridalshower.database.LocalDatabase

@Api("removeItem")
fun removeItem(context: ApiContext) {
    if (context.req.method != HttpMethod.DELETE) return

    val ownerId = context.req.params["ownerId"] ?: return
    val itemId = context.req.params["itemId"] ?: return

    context.data.getValue<LocalDatabase>().remove(ownerId, itemId)
    context.res.status = 200
}

@Api("removeAllItems")
fun removeAllItems(context: ApiContext) {
    if (context.req.method != HttpMethod.DELETE) return

    val ownerId = context.req.params["ownerId"] ?: return

    context.data.getValue<LocalDatabase>().removeAll(ownerId)
    context.res.status = 200
}