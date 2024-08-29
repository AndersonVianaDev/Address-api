package com.anderson.address_api.core.address.usecases.impl;

import com.anderson.address_api.core.address.dataprovider.AddressRepository;
import com.anderson.address_api.core.address.usecases.ports.DeleteAddressByIdUseCasePort;
import com.anderson.address_api.core.address.usecases.ports.FindAddressByIdUseCasePort;

import java.util.UUID;

public class DeleteAddressByIdUseCaseImpl implements DeleteAddressByIdUseCasePort {

    private final AddressRepository repository;
    private final FindAddressByIdUseCasePort findAddressById;

    public DeleteAddressByIdUseCaseImpl(AddressRepository repository, FindAddressByIdUseCasePort findAddressById) {
        this.repository = repository;
        this.findAddressById = findAddressById;
    }

    @Override
    public void execute(UUID id) {
        findAddressById.execute(id);
        repository.delete(id);
    }
}
