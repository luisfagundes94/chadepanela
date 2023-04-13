package org.luisjulliana.bridalshower.data.repositories

import com.luisjulliana.bridalshower.domain.repositories.EmailRepository
import com.varabyte.kobweb.browser.api
import kotlinx.browser.window

class EmailRepositoryImpl : EmailRepository {
    override suspend fun sendEmail(
        email: String,
        name: String
    ) {

        try {
            window.api.post(
                apiPath = "$SEND_EMAIL_PATH?email=$email&name=$name"
            )
        } catch (exception: Exception) {
            println(exception.stackTraceToString())
        }
    }

    private companion object {
        const val SEND_EMAIL_PATH = "sendEmail"
    }
}