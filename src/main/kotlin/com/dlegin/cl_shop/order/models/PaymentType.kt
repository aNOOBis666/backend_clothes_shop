package com.dlegin.cl_shop.order.models

sealed interface PaymentType {
    enum class OnReceive : PaymentType {
        CASH, CREDIT_CARD,
    }

    enum class PrePayment : PaymentType {
        CREDIT_CARD
    }
}
