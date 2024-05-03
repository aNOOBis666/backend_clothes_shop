package com.dlegin.cl_shop.order.repository

import com.dlegin.cl_shop.order.models.OrderVO
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository


@Repository
interface OrderRepository : MongoRepository<OrderVO, String> {

    override fun count(): Long
}