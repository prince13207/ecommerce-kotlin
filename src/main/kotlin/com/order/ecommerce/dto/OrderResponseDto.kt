package com.order.ecommerce.dto

import com.order.ecommerce.enum.OrderStatus
import com.order.ecommerce.enum.PaymentMode
import com.order.ecommerce.enum.ShippingMode
import lombok.NonNull
import java.time.LocalDateTime

data class OrderResponseDto (
    @NonNull val customerId: String,
    @NonNull val subTotal: Double,
    @NonNull val totalAmt: Double,
    @NonNull val tax: Double,
    @NonNull val orderStatus : OrderStatus,
    @NonNull val shippingCharges: Double,
    @NonNull val title: String,
    @NonNull val shippingMode: ShippingMode,
    @NonNull var amount: Double,
    @NonNull val paymentMode: PaymentMode,
    @NonNull val billingAddress: AddressDto,
    @NonNull val shippingAddress: AddressDto,
    @NonNull val orderItems: List<OrderItemDto>,
    @NonNull val createdAt : LocalDateTime
)