package com.dlegin.cl_shop.order.models

sealed interface PaymentTypeVO {
    enum class OnReceive : PaymentTypeVO {
        CASH, CREDIT_CARD,
    }

    enum class PrePayment : PaymentTypeVO {
        CREDIT_CARD
    }
}
