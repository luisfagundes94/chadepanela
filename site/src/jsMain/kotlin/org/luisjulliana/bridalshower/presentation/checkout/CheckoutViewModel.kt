package org.luisjulliana.bridalshower.presentation.checkout

import com.luisjulliana.bridalshower.core.DataState
import com.luisjulliana.bridalshower.domain.models.Item
import com.luisjulliana.bridalshower.domain.usecases.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class CheckoutViewModel(
    private val checkUserInformation: CheckUserInformation,
    private val sendEmail: SendEmail,
    private val listItems: ListCartItems,
    private val removeItem: RemoveCartItem,
    private val removeAllItems: RemoveAllCartItems,
    private val coroutineScope: CoroutineScope = CoroutineScope(Dispatchers.Main)
) {
    private val _uiState = MutableStateFlow(CheckoutUiState())
    val uiState: StateFlow<CheckoutUiState> = _uiState

    fun checkUserInfo(
        name: String,
        email: String
    ) = checkUserInformation(name, email)

    fun sendEmailToUser(name: String, email: String) = coroutineScope.launch {
        sendEmail(name, email)
    }

    fun listCartItems() = coroutineScope.launch {
        handleResult(data = listItems())
    }

    fun emptyCart() = coroutineScope.launch {
        removeAllItems()
    }

    private fun handleResult(data: DataState<List<Item>>) {
        when (data) {
            is DataState.Success ->
                _uiState.value = _uiState.value.copy(
                    items = data.result,
                    isEmpty = false,
                    hasError = false
                )

            is DataState.Error ->
                _uiState.value = _uiState.value.copy(
                    items = emptyList(),
                    isEmpty = false,
                    hasError = true
                )

            is DataState.Empty ->
                _uiState.value = _uiState.value.copy(
                    items = emptyList(),
                    isEmpty = true,
                    hasError = false
                )
        }
    }
}