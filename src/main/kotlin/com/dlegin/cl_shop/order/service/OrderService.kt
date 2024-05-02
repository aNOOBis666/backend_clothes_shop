package com.dlegin.cl_shop.order.service

import com.dlegin.cl_shop.order.models.Order
import com.fasterxml.jackson.databind.ObjectMapper
import lombok.extern.slf4j.Slf4j
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.http.HttpEntity
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.stereotype.Service
import org.springframework.web.client.RestTemplate
import org.springframework.web.server.ResponseStatusException


@Service
class OrderService {
    private val log: Logger = LoggerFactory.getLogger(OrderService::class.java)

    @Autowired
    var restTemplate: RestTemplate? = null

    @Value("http://localhost")
    private val customerBaseUrl: String? = null

    fun createOrder(order: Order) {
        val url = customerBaseUrl + CUSTOMER_ORDER_URL + order.customerId
        val headers = HttpHeaders()
        headers.contentType = MediaType.APPLICATION_JSON
        log.info("Order Request URL: {}", url)
        try {
            val request = HttpEntity(order, headers)
            val responseEntity = restTemplate?.postForEntity(url, request, Order::class.java)
            if (responseEntity?.statusCode?.isError == true) {
                log.error(
                    "For Order ID: {}, error response: {} is received to create Order in Customer Microservice",
                    order.id,
                    responseEntity.statusCode
                )
                throw ResponseStatusException(
                    HttpStatus.INTERNAL_SERVER_ERROR,
                    java.lang.String.format(
                        "For Order UUID: %s, Customer Microservice Message: %s",
                        order.id,
                        responseEntity.statusCode
                    )
                )
            }
            if (responseEntity?.hasBody() == true) {
                log.error("Order From Response: {}", responseEntity.body?.id)
            }
        } catch (e: Exception) {
            throw ResponseStatusException(
                HttpStatus.BAD_REQUEST,
                java.lang.String.format(
                    "For Order UUID: %s, Customer Microservice Response: %d",
                    order.id,
                )
            )
        }
    }

    fun getOrders(orders: List<Order>) {
        val url = customerBaseUrl + CUSTOMER_ORDER_URL + orders
        val headers = HttpHeaders()
        headers.contentType = MediaType.APPLICATION_JSON
        log.info("Order Request URL: {}", url)
        try {
            val request = HttpEntity(orders, headers)
            val responseEntity = restTemplate?.postForEntity(url, request, Order::class.java)
            if (responseEntity?.statusCode?.isError == true) {
                log.error(
                    "For Order ID: {}, error response: {} is received to create Order in Customer Microservice",
                    orders,
                    responseEntity.statusCode
                )
                throw ResponseStatusException(
                    HttpStatus.INTERNAL_SERVER_ERROR,
                    java.lang.String.format(
                        "For Order UUID: %s, Customer Microservice Message: %s",
                        orders,
                        responseEntity.statusCode
                    )
                )
            }
            if (responseEntity?.hasBody() == true) {
                log.error("Order From Response: {}", responseEntity.body?.id)
            }
        } catch (e: Exception) {
            throw ResponseStatusException(
                HttpStatus.BAD_REQUEST,
                java.lang.String.format(
                    "For Order UUID: %s, Customer Microservice Response: %d",
                    orders,
                )
            )
        }
    }

    companion object {
        private const val CUSTOMER_ORDER_URL = "customerOrders/"
    }
}