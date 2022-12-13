package com.order.ecommerce.mapper

import com.order.ecommerce.dto.AddressDto
import com.order.ecommerce.dto.OrderItemDto
import com.order.ecommerce.dto.OrderResponseDto
import com.order.ecommerce.enum.PaymentStatus
import com.order.ecommerce.model.Address
import com.order.ecommerce.model.Order
import com.order.ecommerce.model.OrderItem
import com.order.ecommerce.model.OrderItemPk
import com.order.ecommerce.model.Payment
import com.order.ecommerce.repository.AddressRepository
import com.order.ecommerce.repository.OrderItemRepository
import com.order.ecommerce.repository.PaymentRepository
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
                OrderItemPk(orderItemDto.productId, orderId),
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


    private fun buildAddressDto(address: Address) = AddressDto(
        address1 = address.address1,
        address2 = address.address2,
        city = address.city,
        state = address.state,
        zip = address.zip,
        email = address.email,
        phone = address.phone
    )


    private fun buildOrderItemDtoList(orderItemsList: MutableList<OrderItem>?): List<OrderItemDto> {
        if (orderItemsList != null) {
            return orderItemsList.map { orderItem ->
                OrderItemDto(orderItem.product?.productId ?: "", orderItem.quantity)
            }.toList()
        }
        return emptyList();
    }



    fun buildOrderResponseDto(order: Order) = OrderResponseDto(
        customerId = order.customerId,
        subTotal = order.subTotal,
        totalAmt = order.totalAmt,
        tax = order.tax,
        shippingCharges = order.shippingCharges,
        title = order.title,
        shippingMode = order.shippingMode,
        billingAddress = buildAddressDto(order.billingAddress),
        shippingAddress = buildAddressDto(order.shippingAddress),
        paymentMode = order.payment.paymentMode,
        orderItems = buildOrderItemDtoList(order.orderItems),
        amount = order.payment.amount
    )

}
