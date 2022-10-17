package com.pdl.platform.controller

import com.pdl.platform.dto.ProductDto
import com.pdl.platform.model.Product
import com.pdl.platform.service.ProductService
import io.swagger.v3.oas.annotations.Operation
import lombok.extern.slf4j.Slf4j
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/v1")
@Slf4j
class ProductController(val productService: ProductService) {

    @PostMapping("/product")
    @Operation(summary = "Create a product", description = "Create a product")
    fun createOrder(@RequestBody productDto: ProductDto): Product {
        return productService.createProduct(productDto)
    }

    @GetMapping("/product/{podId}")
    @Operation(summary = "Get a product", description = "Get a product")
    fun getProduct(@PathVariable(name = "podId") podId: String): Product {
        return productService.getProduct(podId)
    }

}
