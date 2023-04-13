package org.luisjulliana.bridalshower.presentation.checkout

import com.luisjulliana.bridalshower.domain.usecases.CheckUserInformation
import com.luisjulliana.bridalshower.domain.usecases.SendEmail
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CheckoutViewModel(
    private val checkUserInformation: CheckUserInformation,
    private val sendEmail: SendEmail
) {
    private val coroutineScope = CoroutineScope(Dispatchers.Main)
    fun checkUserInfo(
        name: String,
        email: String
    ) = checkUserInformation(name, email)

    fun sendEmailToUser(name: String, email: String) = coroutineScope.launch {
        sendEmail(name, email)
    }
}