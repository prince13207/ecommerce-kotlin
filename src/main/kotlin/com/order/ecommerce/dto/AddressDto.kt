package com.order.ecommerce.dto

import lombok.NonNull
import javax.validation.constraints.Email

data class AddressDto(

    @NonNull val address1: String,
    @NonNull val address2: String,
    @NonNull val city: String,
    @NonNull val state: String,
    @NonNull val zip: String,
    @NonNull @Email val email: String,
    @NonNull val phone: String

)
