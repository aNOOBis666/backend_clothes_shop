package com.dlegin.cl_shop.market.models

import jakarta.validation.constraints.NotBlank
import lombok.Getter
import lombok.NoArgsConstructor
import lombok.Setter
import lombok.ToString
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import org.springframework.data.mongodb.core.mapping.Field
import java.io.Serializable
import java.util.*

@Document(collection = "address")
@kotlinx.serialization.Serializable
class Address {
    @Id
    val id: String = String()

    @NotBlank
    @Field("customer_id")
    val customerId: String? = null

    @Field("country")
    val country: String? = null

    @Field("city")
    val city: String? = null

    @Field("street")
    val street: String? = null

    @Field("addition")
    val addition: String? = null

    @Field("comment")
    val comment: String? = null
}