package com.luisjulliana.bridalshower.domain.repositories

interface EmailRepository {
    suspend fun sendEmail(
        email: String,
        name: String
    )
}