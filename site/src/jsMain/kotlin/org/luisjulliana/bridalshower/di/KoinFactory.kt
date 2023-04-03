package com.luisjulliana.bridalshower.di

import org.luisjulliana.bridalshower.presentation.WishlistViewModel
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

object KoinFactory: KoinComponent {
    val wishlistViewModel: WishlistViewModel by inject()
}