package com.anderson.address_api.core.address.usecases.ports;

import com.anderson.address_api.core.address.domain.Address;
import com.anderson.address_api.core.address.dtos.AddressUpdateDTO;

import java.util.UUID;

public interface UpdateAddressUseCasePort {
    Address execute(UUID id, AddressUpdateDTO obj);
}
