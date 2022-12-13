package com.order.ecommerce.model

import com.order.ecommerce.enum.PaymentMode
import com.order.ecommerce.enum.PaymentStatus
import java.io.Serializable
import java.time.LocalDate
import javax.persistence.*

@Entity
@Table(name = "ecommerce_payment")
class Payment(

    @Id
    @Column(name = "payment_id", nullable = false, unique = true)
    open var paymentId: String,

    @Column(name = "amount", nullable = false)
    open var amount: Double,

    @Column(name = "payment_mode", nullable = false)
    open var paymentMode: PaymentMode,

    @Column(name = "confirmation_number", nullable = false)
    open var confirmationNumber: String,

    @Column(name = "payment_status", nullable = false)
    open var paymentStatus: PaymentStatus,

    @Column(name = "createdAt", nullable = false)
    open var createdAt: LocalDate,

    @OneToOne(fetch = FetchType.LAZY, mappedBy = "payment")
    open var order: Order?

) : Serializable
