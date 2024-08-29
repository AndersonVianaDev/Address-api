package com.anderson.address_api.core.address.dataprovider;

import com.anderson.address_api.core.address.domain.Address;

import java.util.Optional;
import java.util.UUID;

public interface AddressRepository {
    Address save(Address address);
    Optional<Address> findByNumberAndZipCode(String number, String zipCode);
    Optional<Address> findById(UUID id);
    void delete(UUID id);
}
