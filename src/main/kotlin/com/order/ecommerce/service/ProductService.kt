package com.order.ecommerce.service

import com.order.ecommerce.dto.ProductDto
import com.order.ecommerce.model.Product

interface ProductService {
    fun createProduct(productDto: ProductDto): Product
    fun getProduct(productId: String): Product
}