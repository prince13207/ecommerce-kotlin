package com.order.ecommerce.service.impl

import com.order.ecommerce.dto.ProductDto
import com.order.ecommerce.model.Product
import com.order.ecommerce.repository.ProductRepository
import com.order.ecommerce.service.ProductService
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import java.time.LocalDate

@Service
class ProductServiceImpl(val productRepository: ProductRepository) : ProductService {

    companion object {
        val log: Logger = LoggerFactory.getLogger(ProductService::class.java)
    }

    override fun createProduct(productDto: ProductDto): Product {
        log.info("Creating Product with productId {}", productDto.productId)
        return productRepository.save(productDto.toProductEntity())

    }

    override fun getProduct(productId: String): Product {
        log.info("Get Product by productId {}", productId)
        return productRepository.findById(productId).orElseThrow()

    }

    fun ProductDto.toProductEntity() = Product(
        productId = productId,
        sku = sku,
        title = title,
        description = description,
        price = price,
        createdAt = LocalDate.now(),
        orderItems = null
    )

}
