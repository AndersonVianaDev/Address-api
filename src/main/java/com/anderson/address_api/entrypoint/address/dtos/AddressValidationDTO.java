package com.anderson.address_api.entrypoint.address.dtos;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record AddressValidationDTO(@NotNull(message = "zip code field cannot be null") @Size(min = 8, message = "field must have at least 8 characters") String zipCode,
                                   String complement,
                                   @NotNull(message = "number field cannot be null") String number) {
}
