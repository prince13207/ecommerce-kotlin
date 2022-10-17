package com.pdl.platform.dto

import lombok.NonNull

data class OrderCreateResponse(
    @NonNull val orderId: String,
    @NonNull val orderStatus: String
)
