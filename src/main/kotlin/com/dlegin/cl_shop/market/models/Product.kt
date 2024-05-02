package com.dlegin.cl_shop.market.models

import jakarta.validation.constraints.NotBlank
import lombok.Getter
import lombok.NoArgsConstructor
import lombok.Setter
import lombok.ToString
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.Id
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.mongodb.core.mapping.Document
import org.springframework.data.mongodb.core.mapping.Field
import java.io.Serializable
import java.time.Instant
import java.util.*


@Document(collection = "good")
@kotlinx.serialization.Serializable
class Product {

    @Id
    val id: String? = null

    @NotBlank
    @Field("display_name")
    val displayName: String? = null

    @NotBlank
    @Field("description")
    val description: String? = null

    @NotBlank
    @Field("sizes")
    val sizes: Set<Sizes>? = null

    @NotBlank
    @Field("composition")
    val composition: String? = null

    @Field("leading_color")
    val leadingColor: String? = null

    @NotBlank
    @Field("vendor_code")
    val vendorCode: Long? = null

    @Field("created_at")
    @CreatedDate
    val createdAt: Long? = null

    @Field("updated_at")
    @LastModifiedDate
    val updatedAt: Long? = null
}