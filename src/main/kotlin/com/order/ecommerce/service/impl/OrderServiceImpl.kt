package com.order.ecommerce.service.impl

import com.order.ecommerce.dto.OrderCreateResponse
import com.order.ecommerce.dto.OrderDto
import com.order.ecommerce.dto.OrderItemDto
import com.order.ecommerce.dto.OrderResponseDto
import com.order.ecommerce.enum.OrderStatus
import com.order.ecommerce.mapper.OrderDetailsMapper
import com.order.ecommerce.model.Order
import com.order.ecommerce.model.OrderItem
import com.order.ecommerce.repository.OrderItemRepository
import com.order.ecommerce.repository.OrderRepository
import com.order.ecommerce.service.OrderService
import com.order.ecommerce.service.ProductService
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import java.time.LocalDateTime
import java.util.*
import javax.transaction.Transactional

@Service
class OrderServiceImpl(
    val orderRepository: OrderRepository,
    val orderDetailsMapper: OrderDetailsMapper,
    val productService: ProductService,
    val orderItemRepository: OrderItemRepository
) : OrderService {

    companion object {
        val log: Logger = LoggerFactory.getLogger(OrderService::class.java)
    }

    override fun updateOrderStatus(orderId: String, orderStatus: OrderStatus) {
        val order: Order = orderRepository.findById(orderId).orElseThrow()
        order.orderStatus = orderStatus
        orderRepository.save(order)
    }

    override fun findOrderById(orderId: String): OrderResponseDto {
        //Always return a dto - Need to map entity to dto to get all fields
        return orderDetailsMapper.buildOrderResponseDto(orderRepository.findById(orderId).orElseThrow());
    }

    @Transactional
    override fun createOrder(orderDto: OrderDto): OrderCreateResponse {
        log.info("Creating Order for customer {}", orderDto.customerId)
        val savedOrder: Order =
            orderRepository.save(orderDto.toOrderEntity(UUID.randomUUID().toString()))

        val orderItemList: List<OrderItem> =
            orderDetailsMapper.buildOrderItems(orderDto.orderItems, savedOrder.orderId)
        orderItemRepository.saveAll(orderItemList)
        //Always return a dto - Need to map entity to dto
        return OrderCreateResponse(savedOrder.orderId, savedOrder.orderStatus)

    }

    private fun calculateTotalAmount(orderItems: List<OrderItemDto>) : Double {
        return orderItems
            .map { orderItem -> productService.getProduct(orderItem.productId).price * orderItem.quantity }
            .reduce{ price1, price2 -> price1 + price2 }
    }


    fun OrderDto.toOrderEntity(orderId: String): Order {

        val totalAmount = calculateTotalAmount(orderItems);

        return Order(
            orderId = orderId,
            orderStatus = OrderStatus.PROCESSING,
            customerId = customerId,
            subTotal = totalAmount+shippingCharges+tax,
            totalAmt = totalAmount,
            tax = tax,
            shippingCharges = shippingCharges,
            title = title,
            shippingMode = shippingMode,
            createdAt = LocalDateTime.now(),
            payment = orderDetailsMapper.buildAndLoadPayment(totalAmount+shippingCharges+tax, paymentMode),
            billingAddress = orderDetailsMapper.buildAndLoadAddress(billingAddress),
            shippingAddress = orderDetailsMapper.buildAndLoadAddress(shippingAddress),
            orderItems = null

        );
    }


}
