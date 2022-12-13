package com.order.ecommerce.dto

import com.order.ecommerce.enum.OrderStatus
import lombok.NonNull

data class OrderCreateResponse(
    @NonNull val orderId: String,
    @NonNull val orderStatus: OrderStatus
)
