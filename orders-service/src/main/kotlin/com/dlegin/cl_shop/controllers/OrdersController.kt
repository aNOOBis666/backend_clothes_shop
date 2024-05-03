package com.dlegin.cl_shop.controllers

import com.dlegin.cl_shop.order.models.OrderVO
import com.dlegin.cl_shop.order.repository.OrderRepository
import com.google.gson.Gson
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.bind.annotation.*
import java.net.URI
import java.net.URISyntaxException


@RestController
@RequestMapping("/api/v1")
class OrdersController {
    @Autowired
    lateinit var orderRepository: OrderRepository

    private val gson = Gson()

    @RequestMapping(
        ORDERS,
        method = [RequestMethod.POST],
        consumes = [MediaType.APPLICATION_JSON_VALUE],
        produces = [MediaType.APPLICATION_JSON_VALUE]
    )
    @Transactional
    @ResponseBody
    @Throws(URISyntaxException::class)
    fun createOrder(@RequestBody orderVO: String): ResponseEntity<String> {
        val order = gson.fromJson(orderVO, OrderVO::class.java)
        val result = orderRepository.save(order)

        return ResponseEntity.created(URI("/api/v1/orders/" + result.id)).body(gson.toJson(result))
    }

    @RequestMapping(
        ORDERS,
        method = [RequestMethod.GET],
        produces = [MediaType.APPLICATION_JSON_VALUE]
    )
    @Transactional
    @Throws(URISyntaxException::class)
    @ResponseBody
    fun getOrders(): ResponseEntity<String> {
        val result = orderRepository.findAll()
        return ResponseEntity.ok(gson.toJson(result))
    }

    companion object {
        private const val ORDERS = "/orders"
    }
}