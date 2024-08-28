package com.anderson.address_api.core.address.usecases.ports;

import com.anderson.address_api.core.address.domain.Address;
import com.anderson.address_api.core.address.dtos.AddressRequestDTO;

public interface RegisterAddressUseCasePort {
    Address execute(AddressRequestDTO obj);
}
