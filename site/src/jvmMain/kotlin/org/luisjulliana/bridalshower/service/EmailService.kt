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
            Olá, $name!

            Muito obrigado(a) por participar do nosso Chá de Panela. Veja abaixo os presentes que você escolheu:
            (inserir aqui imagem do que ele escolheu, com quantidade escolhida e também que tenha o link pra ele ir pra loja ver o modelo e tudo)

            Você pode entregar seu presente de várias formas, veja elas abaixo:
            - O valor orçado pode ser feito em forma de PIX para a seguinte chave 13242267737;
            - Comprar e levar no dia da festa;
            - Comprar e mandar entregar diretamente para nós, no endereço a seguir: Estrada do Rio grande, 1240, bloco 2, casa 101. Taquara. CEP 22720010.

            Esperamos sua presença na festa!
            Será na Estrada do Rio grande, 1240. Condomínio Residencial Reserva do Rio Grande - Salão de Festas Piscina.
            Dia 17/06/2023, às 14h.

            Um abraço,
            Julliana e Luís Felipe.
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
    }
}