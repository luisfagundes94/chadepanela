package com.luisjulliana.bridalshower.domain.usecases

import com.luisjulliana.bridalshower.domain.repositories.EmailRepository

class SendEmail(private val repository: EmailRepository) {
    suspend operator fun invoke(email: String, name: String) =
        repository.sendEmail(email, name)
}