package com.anderson.address_api.core.address.usecases.builders;

import com.anderson.address_api.core.address.builder.AddressBuilder;
import com.anderson.address_api.core.address.domain.Address;
import com.anderson.address_api.core.address.dtos.AddressExternalDTO;
import com.anderson.address_api.core.address.dtos.AddressRequestDTO;
import com.anderson.address_api.core.address.dtos.AddressUpdateDTO;

import java.time.Instant;
import java.util.UUID;

public class AddressTestUnitBuilder {

    public static AddressRequestDTO newAddressRequestDTO() {
        return new AddressRequestDTO("24435567", "234", "complement");
    }

    public static AddressExternalDTO newAddressExternalDTO() {
        return new AddressExternalDTO("24435-567", "BAHIA", "BA", "BAIRRO");
    }

    public static Address newAddress() {
        return new AddressBuilder()
                .withId(UUID.randomUUID())
                .withZipCode("24435-567")
                .withLocality("BAHIA")
                .withUf("BA")
                .withComplement("complement")
                .withNeighborhood("BAIRRO")
                .withNumber("234")
                .withCreatedAt(Instant.now())
                .withUpdatedAt(Instant.now())
                .build();
    }

    public static AddressUpdateDTO newUpdateDTO() {
        return new AddressUpdateDTO("890", "complement 2");
    }

    public static AddressUpdateDTO newUpdateDTOComplementNULL() {
        return new AddressUpdateDTO("890", null);
    }

    public static AddressUpdateDTO newUpdateDTONumberNULL() {
        return new AddressUpdateDTO(null, "complement 2");
    }
}
