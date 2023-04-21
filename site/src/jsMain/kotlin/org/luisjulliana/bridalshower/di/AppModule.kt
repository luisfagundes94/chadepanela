package org.luisjulliana.bridalshower.di

import com.luisjulliana.bridalshower.domain.repositories.EmailRepository
import com.luisjulliana.bridalshower.domain.repositories.ItemRepository
import com.luisjulliana.bridalshower.domain.usecases.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import org.koin.dsl.module
import org.luisjulliana.bridalshower.data.repositories.EmailRepositoryImpl
import org.luisjulliana.bridalshower.data.repositories.RemoteItemRepositoryImpl
import org.luisjulliana.bridalshower.data.service.ItemService
import org.luisjulliana.bridalshower.presentation.checkout.CheckoutViewModel
import org.luisjulliana.bridalshower.presentation.wishlist.WishlistViewModel

val appModule = module {
    // Coroutine Scope
    single { CoroutineScope(Dispatchers.Main) }

    // Services
    single { ItemService() }

    // Repositories
    single<ItemRepository> { RemoteItemRepositoryImpl(get()) }
    single<EmailRepository> { EmailRepositoryImpl() }

    // Usecases
    factory { GetItems(get()) }
    factory { CheckUserInformation() }
    factory { SendEmail(get()) }
    factory { AddItemToCart(get()) }
    factory { ListCartItems(get()) }
    factory { RemoveCartItem(get()) }
    factory { RemoveAllCartItems(get()) }

    // ViewModels
    factory { WishlistViewModel(get(), get(), get(), get()) }
    factory { CheckoutViewModel(get(), get(), get(), get(), get()) }
}