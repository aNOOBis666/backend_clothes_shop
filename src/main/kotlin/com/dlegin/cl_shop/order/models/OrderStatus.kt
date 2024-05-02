package com.dlegin.cl_shop.order.models

sealed interface OrderStatus {
    enum class InProgress : OrderStatus {
        DRAFT
    }

    enum class Completed : OrderStatus {
        CONFIRMED, ON_ASSEMBLY, DELIVERING, IN_SHOP
    }

    enum class Finished : OrderStatus {
        COSTUMER_RECEIVED, REFUND
    }
}