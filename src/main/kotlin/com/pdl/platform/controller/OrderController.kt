package com.pdl.platform.controller

import com.pdl.platform.dto.OrderCreateResponse
import com.pdl.platform.dto.OrderDto
import com.pdl.platform.model.Order
import com.pdl.platform.service.OrderService
import io.swagger.v3.oas.annotations.Operation
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/v1")
class OrderController(val orderService: OrderService) {

    @PostMapping("/order")
    @Operation(summary = "Create an order", description = "Create an order")
    fun createOrder(@RequestBody orderDto: OrderDto): OrderCreateResponse {
        return orderService.createOrder(orderDto)
    }

    @GetMapping("/order/{orderId}")
    fun findOrderById(@PathVariable(name = "orderId") orderId: String): Order {
        return orderService.findOrderById(orderId)
    }

    @PatchMapping("/order/{orderId}")
    fun updateOrderStatus(
        @PathVariable("orderId") orderId: String,
        @RequestParam(name = "orderStatus") orderStatus: String
    ) {
        orderService.updateOrderStatus(orderId, orderStatus)
    }
}
