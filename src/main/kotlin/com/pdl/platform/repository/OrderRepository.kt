package com.pdl.platform.repository

import com.pdl.platform.model.Order
import org.springframework.data.repository.CrudRepository

interface OrderRepository : CrudRepository<Order, String> {

}
