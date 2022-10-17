package com.pdl.platform.dto

import lombok.NonNull

data class ProductDto(
    @NonNull val podId: String,
    @NonNull val sku: String,
    @NonNull val title: String,
    @NonNull val description: String,
    @NonNull val price: Double,
)
