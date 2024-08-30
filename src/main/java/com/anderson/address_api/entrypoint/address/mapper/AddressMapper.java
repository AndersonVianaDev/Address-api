package com.anderson.address_api.entrypoint.address.mapper;

import com.anderson.address_api.core.address.dtos.AddressRequestDTO;
import com.anderson.address_api.core.address.dtos.AddressUpdateDTO;
import com.anderson.address_api.entrypoint.address.dtos.AddressUpdateValidationDTO;
import com.anderson.address_api.entrypoint.address.dtos.AddressValidationDTO;

public class AddressMapper {

    public static AddressRequestDTO toAddressRequestDTO(AddressValidationDTO obj) {
        return new AddressRequestDTO(obj.zipCode(), obj.number(), obj.complement());
    }

    public static AddressUpdateDTO toAddressUpdateDTO(AddressUpdateValidationDTO obj) {
        return new AddressUpdateDTO(obj.number(), obj.complement());
    }
}
