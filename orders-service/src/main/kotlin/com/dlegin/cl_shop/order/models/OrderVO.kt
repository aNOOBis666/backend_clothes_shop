package com.dlegin.cl_shop.order.models

import com.dlegin.cl_shop.market.models.Address
import com.dlegin.cl_shop.market.models.Product

data class OrderVO(
    val id: String? = null,
    val customerId: String? = null,
    val products: Set<Product>? = null,
    val shippingAddress: Address? = null,
    val status: OrderStatusVO? = null,
    val createdAt: Long? = null,
    val updatedAt: Long? = null,
    val paymentStatus: Boolean = false,
    val paymentMethod: PaymentTypeVO? = null,
    val paymentDetails: String? = null,
)