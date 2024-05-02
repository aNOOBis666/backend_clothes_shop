package com.dlegin.cl_shop.order.models

import com.dlegin.cl_shop.market.models.Address
import com.dlegin.cl_shop.market.models.Product
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotEmpty
import jakarta.validation.constraints.NotNull
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.Id
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.mongodb.core.mapping.Document
import org.springframework.data.mongodb.core.mapping.Field


@Document(collection = "order")
@kotlinx.serialization.Serializable
data class Order(

    @Id
    val id: String? = null,

    @NotBlank
    @Field("customer_id")
    val customerId: String? = null,

    @Field("products")
    @NotEmpty
    val products: Set<Product>? = null,

    @Field("shipping_address")
    val shippingAddress: Address? = null,

    @Field("status")
    val status: OrderStatus? = null,

    @Field("created_at")
    @CreatedDate
    val createdAt: Long? = null,

    @Field("updated_at")
    @LastModifiedDate
    val updatedAt: Long? = null,

    @Field("payment_status")
    val paymentStatus: Boolean = false,

    @NotNull
    @Field("payment_method")
    val paymentMethod: PaymentType? = null,

    @NotNull
    @Field("payment_details")
    val paymentDetails: String? = null,
)