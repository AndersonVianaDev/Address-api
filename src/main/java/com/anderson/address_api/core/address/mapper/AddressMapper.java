package com.anderson.address_api.core.address.mapper;

import com.anderson.address_api.core.address.builder.AddressBuilder;
import com.anderson.address_api.core.address.domain.Address;
import com.anderson.address_api.core.address.dtos.AddressExternalDTO;

public class AddressMapper {

    public static Address toAddress(AddressExternalDTO obj, String number, String complement) {
        return new AddressBuilder()
                .withZipCode(obj.cep())
                .withLocality(obj.localidade())
                .withUf(obj.uf())
                .withNumber(number)
                .withNeighborhood(obj.bairro())
                .withComplement(complement)
                .build();
    }
}
