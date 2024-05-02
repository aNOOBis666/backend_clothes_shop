package com.dlegin.cl_shop.order.repository

import com.dlegin.cl_shop.order.models.Order
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.data.mongodb.repository.Query
import org.springframework.stereotype.Repository


@Repository
interface OrderRepository : MongoRepository<Order, String> {

    override fun count(): Long
}