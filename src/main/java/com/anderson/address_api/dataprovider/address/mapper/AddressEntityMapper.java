package com.anderson.address_api.dataprovider.address.mapper;

import com.anderson.address_api.core.address.builder.AddressBuilder;
import com.anderson.address_api.core.address.domain.Address;
import com.anderson.address_api.dataprovider.address.builder.AddressEntityBuilder;
import com.anderson.address_api.dataprovider.address.entity.AddressEntity;

import java.util.Optional;

public class AddressEntityMapper {

    public static AddressEntity toAddressEntity(Address address) {
        return new AddressEntityBuilder()
                .withZipCode(address.getZipCode())
                .withLocality(address.getLocality())
                .withUf(address.getUf())
                .withNeighborhood(address.getNeighborhood())
                .withComplement(address.getComplement())
                .withNumber(address.getNumber())
                .build();
    }

    public static Address toAddress(AddressEntity obj) {
        return new AddressBuilder()
                .withId(obj.getId())
                .withZipCode(obj.getZipCode())
                .withLocality(obj.getLocality())
                .withUf(obj.getUf())
                .withNeighborhood(obj.getNeighborhood())
                .withComplement(obj.getComplement())
                .withNumber(obj.getNumber())
                .withCreatedAt(obj.getCreatedAt())
                .withUpdatedAt(obj.getUpdatedAt())
                .build();
    }

    public static Optional<Address> toOptionalAddress(Optional<AddressEntity> obj) {
        return obj.map(AddressEntityMapper::toAddress);
    }
}
