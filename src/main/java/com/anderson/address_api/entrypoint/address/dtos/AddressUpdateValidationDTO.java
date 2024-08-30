package com.anderson.address_api.entrypoint.address.dtos;

import jakarta.validation.constraints.NotNull;

public record AddressUpdateValidationDTO(@NotNull(message = "number field cannot be null") String number, String complement) {
}
