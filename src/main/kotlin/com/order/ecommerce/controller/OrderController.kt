package com.order.ecommerce.controller

import com.order.ecommerce.dto.OrderCreateResponse
import com.order.ecommerce.dto.OrderDto
import com.order.ecommerce.dto.OrderResponseDto
import com.order.ecommerce.enum.OrderStatus
import com.order.ecommerce.service.OrderService
import io.swagger.v3.oas.annotations.Operation
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/v1")
class OrderController(val orderService: OrderService) {

    @PostMapping("/orders")
    @Operation(summary = "Create an order", description = "Create an order")
    fun createOrder(@RequestBody orderDto: OrderDto): OrderCreateResponse {
        return orderService.createOrder(orderDto)
    }

    @GetMapping("/orders/{orderId}")
    @Operation(summary = "Get order details by order ID", description = "Get order details by order ID")
    fun findOrderById(@PathVariable(name = "orderId") orderId: String): OrderResponseDto {
        return orderService.findOrderById(orderId)
    }

    @PatchMapping("/orders/{orderId}")
    @Operation(summary = "Update order status by order ID", description = "Update order status by order ID")
    fun updateOrderStatus(
        @PathVariable("orderId") orderId: String,
        @RequestParam(name = "orderStatus") orderStatus: OrderStatus
    ) {
        orderService.updateOrderStatus(orderId, orderStatus)
    }
}
