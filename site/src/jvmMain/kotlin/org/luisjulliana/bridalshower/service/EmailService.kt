package org.luisjulliana.bridalshower.service

import org.apache.commons.mail.DefaultAuthenticator
import org.apache.commons.mail.SimpleEmail

class EmailService {
    fun send(
        to: String,
        name: String,
    ) {
        val email = SimpleEmail()
        setUpNetwork(email)
        createEmail(email, name, to)
        email.send()
    }

    private fun createEmail(
        email: SimpleEmail,
        name: String,
        to: String
    ) {
        val bodyMessage = """
            Olá, $name
            $BODY
        """.trimIndent()
        email.apply {
            subject = SUBJECT
            setMsg(bodyMessage)
            addTo(to)
        }
    }

    private fun setUpNetwork(email: SimpleEmail) {
        email.apply {
            hostName = HOST_NAME
            setSmtpPort(PORT)
            setAuthenticator(DefaultAuthenticator(FROM_EMAIL, FROM_PASSWORD))
            isSSLOnConnect = true
            setFrom(FROM_EMAIL)
        }
    }


    private companion object {
        const val HOST_NAME = "smtp.gmail.com"
        const val FROM_EMAIL = "luisfelipecf94@gmail.com"
        const val FROM_PASSWORD = "mngpzyxhtqkhbqez"
        const val PORT = 465
        const val SUBJECT = "Chá de Panela Julliana e Luís Felipe"
        const val BODY = "Mais que obrigação ter marcado presença. Traga comida e dinheiro, por favor."
    }
}