package com.anderson.address_api.entrypoint.builder;

import com.anderson.address_api.entrypoint.address.dtos.AddressUpdateValidationDTO;
import com.anderson.address_api.entrypoint.address.dtos.AddressValidationDTO;

public class AddressTestIntBuilder {

    public static AddressValidationDTO newAddressValidationDTO() {
        return new AddressValidationDTO("24416060", "aleatorio", "345");
    }

    public static AddressUpdateValidationDTO newAddressUpdateValidationDTO() {
        return new AddressUpdateValidationDTO("288", "rua 2");
    }
}
