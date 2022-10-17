package com.pdl.platform.repository

import com.pdl.platform.model.OrderItem
import com.pdl.platform.model.OrderItemPk
import org.springframework.data.repository.CrudRepository

interface OrderItemRepository : CrudRepository<OrderItem, OrderItemPk> {
}
