package com.pdl.platform.service

import com.pdl.platform.dto.ProductDto
import com.pdl.platform.model.Product
import com.pdl.platform.repository.ProductRepository
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import java.time.LocalDate

@Service
class ProductService(val productRepository: ProductRepository) {

    companion object {
        val log: Logger = LoggerFactory.getLogger(ProductService::class.java)
    }

    fun createProduct(productDto: ProductDto): Product {
        log.info("Creating Product with podId {}", productDto.podId)
        //map productEntity to productDto
        return productRepository.save(productDto.toProductEntity())

    }

    fun getProduct(podId: String): Product {
        log.info("Get Product by podId {}", podId)
        //map productEntity to productDto
        return productRepository.findById(podId).orElseThrow()

    }

    fun ProductDto.toProductEntity() = Product(
        podId = podId,
        sku = sku,
        title = title,
        description = description,
        price = price,
        createdAt = LocalDate.now(),
        orderItems = null
    )

}
