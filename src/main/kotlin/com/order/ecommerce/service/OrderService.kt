package com.order.ecommerce.service

import com.order.ecommerce.dto.OrderCreateResponse
import com.order.ecommerce.dto.OrderDto
import com.order.ecommerce.dto.OrderResponseDto
import com.order.ecommerce.enum.OrderStatus

interface OrderService {

    fun updateOrderStatus(orderId: String, orderStatus: OrderStatus)
    fun findOrderById(orderId: String): OrderResponseDto
    fun createOrder(orderDto: OrderDto): OrderCreateResponse
}