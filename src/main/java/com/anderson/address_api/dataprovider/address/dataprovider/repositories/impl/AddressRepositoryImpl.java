package com.anderson.address_api.dataprovider.address.dataprovider.repositories.impl;

import com.anderson.address_api.core.address.dataprovider.AddressRepository;
import com.anderson.address_api.core.address.domain.Address;
import com.anderson.address_api.core.exceptions.NotFoundException;
import com.anderson.address_api.dataprovider.address.dataprovider.repositories.port.SpringAddressRepository;
import com.anderson.address_api.dataprovider.address.entity.AddressEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

import static com.anderson.address_api.core.exceptions.constants.ExceptionConstants.ADDRESS_NOT_FOUND;
import static com.anderson.address_api.dataprovider.address.mapper.AddressEntityMapper.*;

@Repository
public class AddressRepositoryImpl implements AddressRepository {

    private final SpringAddressRepository repository;

    @Autowired
    public AddressRepositoryImpl(SpringAddressRepository repository) {
        this.repository = repository;
    }

    @Override
    public Address save(Address address) {
        AddressEntity obj = toAddressEntity(address);

        return toAddress(repository.save(obj));
    }

    @Override
    public Optional<Address> findByNumberAndZipCode(String number, String zipCode) {
        return toOptionalAddress(repository.findByNumberAndZipCode(number, zipCode));
    }

    @Override
    public Optional<Address> findById(UUID id) {
        return toOptionalAddress(repository.findById(id));
    }

    @Override
    public void delete(UUID id) {
        AddressEntity obj = repository.findById(id).orElseThrow(() -> new NotFoundException(ADDRESS_NOT_FOUND));
        repository.delete(obj);
    }

    @Override
    public Address update(Address address) {
        AddressEntity obj = repository.findById(address.getId()).orElseThrow(() -> new NotFoundException(ADDRESS_NOT_FOUND));
        obj.setNumber(address.getNumber());
        obj.setComplement(address.getComplement());
        return toAddress(repository.save(obj));
    }
}
