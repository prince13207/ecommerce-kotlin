package com.pdl.platform.repository

import com.pdl.platform.model.Payment
import org.springframework.data.repository.CrudRepository

interface PaymentRepository : CrudRepository<Payment, String> {
}
