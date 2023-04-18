package org.luisjulliana.bridalshower.api

import com.mongodb.client.model.Filters.`in`
import com.varabyte.kobweb.api.Api
import com.varabyte.kobweb.api.ApiContext
import com.varabyte.kobweb.api.http.HttpMethod
import com.varabyte.kobweb.api.http.setBodyText
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import org.bson.Document
import org.bson.conversions.Bson
import org.luisjulliana.bridalshower.api.RequestStatus.setErrorMessage
import org.luisjulliana.bridalshower.api.RequestStatus.setOkMessage
import org.luisjulliana.bridalshower.database.MongoDbDatabase
import org.luisjulliana.bridalshower.service.EmailService

private val emailService = EmailService()

@Api("items")
suspend fun items(context: ApiContext) {
    val statusFilter = setUpStatusFilter(context)
    val categoryFilter = setUpCategoryFilter(context)
    val bodyText = Json.encodeToString(
        MongoDbDatabase.getItems(
            status = statusFilter,
            category = categoryFilter
        )
    )
    if (bodyText.isBlank()) context.res.setBodyText(text = "empty")
    else context.res.setBodyText(text = bodyText)
}

@Api("sendEmail")
fun sendEmail(context: ApiContext) {
    if (context.req.method != HttpMethod.POST) return

    val name = context.req.params["name"]
    val email = context.req.params["email"]

    if (email == null || name == null) {
        setErrorMessage(context)
        return
    }
    try {
        emailService.send(
            email,
            name
        )
    } catch (exception: Exception) {
        setErrorMessage(context)
        return
    }
    setOkMessage(context, "ok")
}

private fun setUpStatusFilter(context: ApiContext): Bson {
    val statusParam = context.req.params["status"]

    return if (statusParam != null) `in`("status", statusParam)
    else Document()
}

private fun setUpCategoryFilter(context: ApiContext): Bson {
    val categoryParam = context.req.params["categoria"]

    return if (categoryParam != null) `in`("categoria", categoryParam)
    else Document()
}
