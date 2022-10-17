package com.pdl.platform.repository

import com.pdl.platform.model.Address
import org.springframework.data.repository.CrudRepository

interface AddressRepository : CrudRepository<Address, String> {
}
