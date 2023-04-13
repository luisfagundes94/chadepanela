package com.luisjulliana.bridalshower.domain.usecases

class CheckUserInformation {
    operator fun invoke(name: String, email: String) =
        name.isNotBlank() && email.isNotBlank()
}