package com.order.ecommerce.util

import com.order.ecommerce.dto.AddressDto
import com.order.ecommerce.dto.OrderDto
import com.order.ecommerce.dto.OrderItemDto
import com.order.ecommerce.dto.OrderResponseDto
import com.order.ecommerce.enum.OrderStatus
import com.order.ecommerce.enum.PaymentMode
import com.order.ecommerce.enum.ShippingMode
import com.order.ecommerce.model.Address
import com.order.ecommerce.model.Order
import com.order.ecommerce.model.OrderItem
import com.order.ecommerce.model.Payment
import java.time.LocalDateTime

class OrderUtil {

    companion object {

        @JvmStatic
        fun createTestOrder(): OrderDto {
            return OrderDto(
                customerId = "1",
                tax = 2.0,
                shippingCharges = 2.0,
                title = "test",
                shippingMode = ShippingMode.DELIVERY,
                paymentMode = PaymentMode.CREDIT,
                billingAddress = createAddress(),
                shippingAddress = createAddress(),
                orderItems = listOf(
                    OrderItemDto("101", 10),
                    OrderItemDto("102", 10)
                )
            )
        }

        @JvmStatic
        fun createAddress() = AddressDto(
            address1 = "3755 M lane",
            address2 = "Apt 311",
            city = "Aurora",
            state = "IL",
            zip = "60504",
            email = "test.gmail.com",
            phone = "1234567890"
        )

        @JvmStatic
        fun createMockOrderResponse(): OrderResponseDto {

            val billingAddress = AddressDto("adress1","address2","city","state","123213","asd@xyz.com","23123123")
            val shippingAddress = AddressDto("adress1","address2","city","state","123213","asd@xyz.com","23123123")
            val itemList = emptyList<OrderItemDto>()

            val dateTime = LocalDateTime.parse("2022-10-17T11:31:27.771692");
            return OrderResponseDto(
                "2e99fe21-2243-4004-9640-e992bbcc5040",
                150.0,
                130.0,
                10.0,
                OrderStatus.PROCESSING,
                10.0,
                "test order",
                ShippingMode.DELIVERY,
                50.0,
                PaymentMode.CREDIT,
                billingAddress,
                shippingAddress,
                itemList,
                dateTime
            )
        }

    }

}
