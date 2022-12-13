package com.order.ecommerce.dto

import com.order.ecommerce.enum.PaymentMode
import com.order.ecommerce.enum.ShippingMode
import lombok.NonNull

data class OrderDto(

    @NonNull val customerId: String,
    @NonNull val tax: Double,
    @NonNull val shippingCharges: Double,
    @NonNull val title: String,
    @NonNull val shippingMode: ShippingMode,
    @NonNull val paymentMode: PaymentMode,
    @NonNull val billingAddress: AddressDto,
    @NonNull val shippingAddress: AddressDto,
    @NonNull val orderItems: List<OrderItemDto>
)
