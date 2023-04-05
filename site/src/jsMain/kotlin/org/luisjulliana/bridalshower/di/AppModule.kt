package org.luisjulliana.bridalshower.di

import com.luisjulliana.bridalshower.domain.repositories.ItemRepository
import com.luisjulliana.bridalshower.domain.usecases.GetItems
import org.koin.dsl.module
import org.luisjulliana.bridalshower.data.repositories.RemoteItemRepositoryImpl
import org.luisjulliana.bridalshower.data.service.ItemService
import org.luisjulliana.bridalshower.presentation.WishlistViewModel

val appModule = module {
    single { ItemService() }
    single<ItemRepository> { RemoteItemRepositoryImpl(get()) }
    factory { GetItems(get()) }
    factory { WishlistViewModel(get()) }
}