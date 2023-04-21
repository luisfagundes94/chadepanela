package org.luisjulliana.bridalshower.api

import com.varabyte.kobweb.api.Api
import com.varabyte.kobweb.api.ApiContext
import com.varabyte.kobweb.api.http.HttpMethod
import com.varabyte.kobweb.api.http.setBodyText
import java.util.*

@Api
fun generateId(context: ApiContext) {
    if (context.req.method != HttpMethod.GET) return
    context.res.setBodyText(UUID.randomUUID().toString())
}