package com.luisjulliana.bridalshower.di

import org.luisjulliana.bridalshower.data.repositories.RemoteItemRepositoryImpl
import org.luisjulliana.bridalshower.data.service.ItemService
import com.luisjulliana.bridalshower.domain.repositories.ItemRepository
import com.luisjulliana.bridalshower.domain.usecases.GetItems
import org.luisjulliana.bridalshower.presentation.WishlistViewModel
import org.koin.dsl.module

val appModule = module {
    single { ItemService() }
    single<ItemRepository> { RemoteItemRepositoryImpl(get()) }
    factory { GetItems(get()) }
    factory { WishlistViewModel(get()) }
}