package com.anderson.address_api.core.address.usecases.impl;

import com.anderson.address_api.core.address.dataprovider.AddressRepository;
import com.anderson.address_api.core.address.domain.Address;
import com.anderson.address_api.core.address.usecases.ports.FindAddressByIdUseCasePort;
import com.anderson.address_api.core.exceptions.NotFoundException;

import java.util.UUID;

import static com.anderson.address_api.core.exceptions.constants.ExceptionConstants.ADDRESS_NOT_FOUND;

public class FindAddressByIdUseCaseImpl implements FindAddressByIdUseCasePort {

    private final AddressRepository repository;

    public FindAddressByIdUseCaseImpl(AddressRepository repository) {
        this.repository = repository;
    }

    @Override
    public Address execute(UUID id) {
        return repository.findById(id).orElseThrow(() -> new NotFoundException(ADDRESS_NOT_FOUND));
    }
}
