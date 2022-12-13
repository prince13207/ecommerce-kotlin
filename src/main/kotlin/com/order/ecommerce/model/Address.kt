package com.order.ecommerce.model

import java.io.Serializable
import java.time.LocalDate
import javax.persistence.*

@Entity
@Table(name = "ecommerce_address")
class Address(

    @Id
    @Column(name = "address_id", nullable = false, unique = true)
    private var addressId: String,

    @Column(name = "address1", nullable = false) var address1: String,

    @Column(name = "address2", nullable = false) var address2: String,

    @Column(name = "city", nullable = false) var city: String,

    @Column(name = "state", nullable = false) var state: String,

    @Column(name = "zip", nullable = false) var zip: String,

    @Column(name = "email", nullable = false) var email: String,

    @Column(name = "phone", nullable = false) var phone: String,

    @Column(name = "createdAt", nullable = false)
    private var createdAt: LocalDate,

    @OneToOne(fetch = FetchType.LAZY, mappedBy = "billingAddress")
    private var order: Order?

) : Serializable
