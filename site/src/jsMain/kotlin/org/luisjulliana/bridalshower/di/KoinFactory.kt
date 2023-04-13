package org.luisjulliana.bridalshower.di

import org.luisjulliana.bridalshower.presentation.wishlist.WishlistViewModel
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import org.luisjulliana.bridalshower.presentation.checkout.CheckoutViewModel

object KoinFactory: KoinComponent {
    val wishlistViewModel: WishlistViewModel by inject()
    val checkoutViewModel: CheckoutViewModel by inject()
}