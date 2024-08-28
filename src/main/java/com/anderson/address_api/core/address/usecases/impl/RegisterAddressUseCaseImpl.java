package com.anderson.address_api.core.address.usecases.impl;

import com.anderson.address_api.core.address.dataprovider.ConsultZipCode;
import com.anderson.address_api.core.address.domain.Address;
import com.anderson.address_api.core.address.dtos.AddressExternalDTO;
import com.anderson.address_api.core.address.dtos.AddressRequestDTO;
import com.anderson.address_api.core.address.dataprovider.AddressRepository;
import com.anderson.address_api.core.address.usecases.ports.RegisterAddressUseCasePort;
import com.anderson.address_api.core.exceptions.DataConflictException;

import static com.anderson.address_api.core.address.mapper.AddressMapper.toAddress;
import static com.anderson.address_api.core.exceptions.constants.ExceptionConstants.ADDRESS_ALREADY_REGISTERED;

public class RegisterAddressUseCaseImpl implements RegisterAddressUseCasePort {

    private final AddressRepository repository;
    private final ConsultZipCode consultZipCode;

    public RegisterAddressUseCaseImpl(AddressRepository repository, ConsultZipCode consultZipCode) {
        this.repository = repository;
        this.consultZipCode = consultZipCode;
    }

    @Override
    public Address execute(AddressRequestDTO obj) {
        if(repository.findByNumberAndZipCode(obj.number(), obj.zipCode()).isPresent()) {
            throw new DataConflictException(ADDRESS_ALREADY_REGISTERED);
        }

        AddressExternalDTO dto = consultZipCode.getAddress(obj.zipCode());

        Address address = toAddress(dto, obj.number(), obj.complement());

        return repository.save(address);
    }
}
