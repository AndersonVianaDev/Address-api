package com.anderson.address_api.core.address.usecases.ports;

import com.anderson.address_api.core.address.domain.Address;

import java.util.UUID;

public interface FindAddressByIdUseCasePort {
    Address execute(UUID id);
}
