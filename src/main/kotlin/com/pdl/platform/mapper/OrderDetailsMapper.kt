package com.pdl.platform.mapper

import com.pdl.platform.dto.AddressDto
import com.pdl.platform.dto.OrderItemDto
import com.pdl.platform.enum.PaymentStatus
import com.pdl.platform.model.Address
import com.pdl.platform.model.OrderItem
import com.pdl.platform.model.OrderItemPk
import com.pdl.platform.model.Payment
import com.pdl.platform.repository.AddressRepository
import com.pdl.platform.repository.OrderItemRepository
import com.pdl.platform.repository.PaymentRepository
import org.springframework.stereotype.Component
import java.time.LocalDate
import java.util.*

@Component
class OrderDetailsMapper(
    private val orderItemRepository: OrderItemRepository,
    private val addressRepository: AddressRepository,
    private val paymentRepository: PaymentRepository
) {

    fun buildAndLoadPayment(amount: Double, paymentMode: String): Payment {
        val payment = Payment(
            UUID.randomUUID().toString(),
            amount,
            paymentMode,
            UUID.randomUUID().toString(),
            PaymentStatus.PROCESSING.name,
            LocalDate.now(),
            null
        )
        paymentRepository.save(payment)
        return payment;
    }

    fun buildAndLoadAddress(addressDto: AddressDto): Address {
        val addressEntity = addressDto.toAddressEntity();
        return addressRepository.save(addressEntity)
    }

    fun buildOrderItems(
        orderItemsDtoList: List<OrderItemDto>,
        orderId: String
    ): MutableList<OrderItem> {
        val orderItemList = orderItemsDtoList.map { orderItemDto ->
            OrderItem(
                OrderItemPk(orderItemDto.podId, orderId),
                null,
                null,
                orderItemDto.quantity
            )
        }
            .toList()
        return orderItemRepository.saveAll(orderItemList) as MutableList<OrderItem>
    }


    private fun AddressDto.toAddressEntity() = Address(
        addressId = UUID.randomUUID().toString(),
        address1 = address1,
        address2 = address2,
        city = city,
        state = state,
        zip = zip,
        email = email,
        phone = phone,
        createdAt = LocalDate.now(),
        order = null
    )

}
