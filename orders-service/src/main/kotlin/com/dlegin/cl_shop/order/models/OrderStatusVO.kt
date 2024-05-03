package com.dlegin.cl_shop.order.models

sealed interface OrderStatusVO {
    enum class InProgress : OrderStatusVO {
        DRAFT
    }

    enum class Completed : OrderStatusVO {
        CONFIRMED, ON_ASSEMBLY, DELIVERING, IN_SHOP
    }

    enum class Finished : OrderStatusVO {
        COSTUMER_RECEIVED, REFUND
    }
}