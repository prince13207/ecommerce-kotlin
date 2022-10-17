package com.pdl.platform.model

import java.io.Serializable
import javax.persistence.Column
import javax.persistence.Embeddable

@Embeddable
class OrderItemPk(

    @Column(name = "pod_id", nullable = false)
    private var podId: String,

    @Column(name = "order_id", nullable = false)
    private var orderId: String

) : Serializable {
    companion object {
        private const val serialVersionUID = 5074947294298476737L
    }
}
