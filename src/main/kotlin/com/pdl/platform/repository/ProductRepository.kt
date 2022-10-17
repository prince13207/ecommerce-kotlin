package com.pdl.platform.repository

import com.pdl.platform.model.Product
import org.springframework.data.repository.CrudRepository

interface ProductRepository : CrudRepository<Product, String> {
}
