package org.luisjulliana.bridalshower.di

import com.luisjulliana.bridalshower.domain.repositories.EmailRepository
import com.luisjulliana.bridalshower.domain.repositories.ItemRepository
import com.luisjulliana.bridalshower.domain.usecases.CheckUserInformation
import com.luisjulliana.bridalshower.domain.usecases.GetItems
import com.luisjulliana.bridalshower.domain.usecases.SendEmail
import org.koin.dsl.module
import org.luisjulliana.bridalshower.data.repositories.EmailRepositoryImpl
import org.luisjulliana.bridalshower.data.repositories.RemoteItemRepositoryImpl
import org.luisjulliana.bridalshower.data.service.ItemService
import org.luisjulliana.bridalshower.presentation.checkout.CheckoutViewModel
import org.luisjulliana.bridalshower.presentation.wishlist.WishlistViewModel

val appModule = module {
    // Services
    single { ItemService() }

    // Repositories
    single<ItemRepository> { RemoteItemRepositoryImpl(get()) }
    single<EmailRepository> { EmailRepositoryImpl() }

    // Usecases
    factory { GetItems(get()) }
    factory { CheckUserInformation() }
    factory { SendEmail(get()) }

    // ViewModels
    factory { WishlistViewModel(get()) }
    factory { CheckoutViewModel(get(), get()) }
}