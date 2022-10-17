package com.pdl.platform.util

import com.pdl.platform.dto.AddressDto
import com.pdl.platform.dto.OrderDto
import com.pdl.platform.dto.OrderItemDto
import com.pdl.platform.enum.PaymentMode
import com.pdl.platform.enum.ShippingMode
import com.pdl.platform.model.Address
import com.pdl.platform.model.Order
import com.pdl.platform.model.OrderItem
import com.pdl.platform.model.Payment
import java.time.LocalDateTime

class OrderUtil {

    companion object {

        @JvmStatic
        fun createTestOrder(): OrderDto {
            return OrderDto(
                customerId = "1",
                subTotal = 6.0,
                totalAmt = 10.0,
                tax = 2.0,
                shippingCharges = 2.0,
                title = "test",
                shippingMode = ShippingMode.DELIVERY.name,
                amount = 10.0,
                paymentMode = PaymentMode.CREDIT.name,
                billingAddress = createAddress(),
                shippingAddress = createAddress(),
                orderItems = listOf(
                    OrderItemDto("101", "10"),
                    OrderItemDto("102", "10")
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
        fun createMockOrderResponse(): Order {

            val payment = Payment::class.java.getConstructor().newInstance()
            val billingAddress = Address::class.java.getConstructor().newInstance()
            val shippingAddress = Address::class.java.getConstructor().newInstance()
            val mutableList = mutableListOf<OrderItem>()

            val dateTime = LocalDateTime.parse("2022-10-17T11:31:27.771692");
            return Order(
                "2e99fe21-2243-4004-9640-e992bbcc5040",
                "PROCESSING",
                "2",
                6.0,
                10.0,
                2.0,
                2.0,
                "testProduct",
                "delivery",
                dateTime,
                payment,
                billingAddress,
                shippingAddress,
                mutableList
            )
        }

    }

}
