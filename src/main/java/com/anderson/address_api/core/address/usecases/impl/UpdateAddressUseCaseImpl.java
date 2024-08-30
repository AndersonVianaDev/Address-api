package com.anderson.address_api.core.address.usecases.impl;

import com.anderson.address_api.core.address.dataprovider.AddressRepository;
import com.anderson.address_api.core.address.domain.Address;
import com.anderson.address_api.core.address.dtos.AddressUpdateDTO;
import com.anderson.address_api.core.address.usecases.ports.FindAddressByIdUseCasePort;
import com.anderson.address_api.core.address.usecases.ports.UpdateAddressUseCasePort;

import java.util.Objects;
import java.util.UUID;

import static java.util.Objects.nonNull;

public class UpdateAddressUseCaseImpl implements UpdateAddressUseCasePort {

    private final AddressRepository repository;
    private final FindAddressByIdUseCasePort findAddressById;

    public UpdateAddressUseCaseImpl(AddressRepository repository, FindAddressByIdUseCasePort findAddressById) {
        this.repository = repository;
        this.findAddressById = findAddressById;
    }

    @Override
    public Address execute(UUID id, AddressUpdateDTO obj) {
        Address address = findAddressById.execute(id);

        if(!Objects.equals(address.getComplement(), obj.complement()) && nonNull(obj.complement())) {
            address.setComplement(obj.complement());
        }

        if(!Objects.equals(address.getNumber(), obj.number()) && nonNull(obj.number())) {
            address.setNumber(obj.number());
        }

        return repository.update(address);
    }
}
