package com.order.ecommerce.dto

import lombok.NonNull

data class OrderDto(

    @NonNull val customerId: String,
    @NonNull val tax: Double,
    @NonNull val shippingCharges: Double,
    @NonNull val title: String,
    @NonNull val shippingMode: String,
    @NonNull val paymentMode: String,
    @NonNull val billingAddress: AddressDto,
    @NonNull val shippingAddress: AddressDto,
    @NonNull val orderItems: List<OrderItemDto>
)
