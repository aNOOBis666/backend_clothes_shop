package com.dlegin.cl_shop.controllers

import com.dlegin.cl_shop.order.models.Order
import com.dlegin.cl_shop.order.repository.OrderRepository
import com.dlegin.cl_shop.order.service.OrderService
import jakarta.validation.Valid
import org.bson.json.JsonObject
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.configurationprocessor.json.JSONArray
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.bind.annotation.*
import org.springframework.web.server.ResponseStatusException
import java.net.URI
import java.net.URISyntaxException


@RestController
@RequestMapping("/api/v1")
class OrdersController {
    @Autowired
    lateinit var orderRepository: OrderRepository

    @Autowired
    lateinit var orderService: OrderService

    private val log = LoggerFactory.getLogger(OrdersController::class.java)

    @Value("\${spring.application.name}")
    private val applicationName: String? = null

    @RequestMapping(
        "/orders",
        method = [RequestMethod.POST],
        consumes = ["text/plain"],
        produces = ["text/plain"]
    )
    @Transactional
    @ResponseBody
    @Throws(URISyntaxException::class)
    fun createOrder(@RequestBody customerId: String): ResponseEntity<String> {
//        log.debug("REST request to save Order : {}", order)
//        if (order.id != null) {
//            throw ResponseStatusException(HttpStatus.CONFLICT, "A new order cannot already have an ID")
//        }


        val result = orderRepository.save<Order>(Order(customerId = customerId))
        result.let { orderService.createOrder(it) }
        val headers = HttpHeaders()
        val message =
            java.lang.String.format("A new %s is created with identifier %s", ENTITY_NAME, result.id.toString())
        headers.add("X-$applicationName-alert", message)
        headers.add("X-$applicationName-params", result.id.toString())
        return ResponseEntity.created(URI("/api/v1/orders/" + result.id)).headers(headers).body(result.toString())
    }

    @RequestMapping(
        "/orders",
        method = [RequestMethod.GET],
        produces = ["text/plain"]
    )
    @Transactional
    @Throws(URISyntaxException::class)
    fun getOrders(): ResponseEntity<String> {

        val result = orderRepository.findAll()
        orderService.getOrders(result)

        return ResponseEntity.ok(result.toString())
    }

    companion object {
        private const val ENTITY_NAME = "order"
    }
}