package org.luisjulliana.bridalshower.api

import com.varabyte.kobweb.api.ApiContext
import com.varabyte.kobweb.api.http.setBodyText

object RequestStatus {
    fun setErrorMessage(context: ApiContext) {
        context.res.status = 400
        context.res.setBodyText("Email or name is empty")
    }

    fun setOkMessage(context: ApiContext, message: String) {
        context.res.status = 200
        context.res.setBodyText(message)
    }
}