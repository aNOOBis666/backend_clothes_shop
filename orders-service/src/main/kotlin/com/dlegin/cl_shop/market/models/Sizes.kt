package com.dlegin.cl_shop.market.models

sealed interface Sizes {
    enum class Clothes : Sizes {
        XS, S, M, L, XL, XXL
    }

    data class Shoes(private val size: Int) : Sizes
}