package com.anderson.address_api.core.address.usecases.impl;

import com.anderson.address_api.core.address.dataprovider.AddressRepository;
import com.anderson.address_api.core.address.dataprovider.ConsultZipCode;
import com.anderson.address_api.core.address.domain.Address;
import com.anderson.address_api.core.address.dtos.AddressExternalDTO;
import com.anderson.address_api.core.address.dtos.AddressRequestDTO;
import com.anderson.address_api.core.exceptions.DataConflictException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static com.anderson.address_api.core.address.usecases.builders.AddressTestUnitBuilder.*;
import static com.anderson.address_api.core.exceptions.constants.ExceptionConstants.ADDRESS_ALREADY_REGISTERED;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class RegisterAddressUseCaseImplTest {

    @InjectMocks
    private RegisterAddressUseCaseImpl register;

    @Mock
    private AddressRepository repository;

    @Mock
    private ConsultZipCode consultZipCode;

    @Test
    @DisplayName("registering address successfully")
    void registerSuccessfully() {
        AddressRequestDTO obj = newAddressRequestDTO();
        AddressExternalDTO dto = newAddressExternalDTO();

        when(repository.findByNumberAndZipCode(obj.number(), obj.zipCode())).thenReturn(Optional.empty());
        when(consultZipCode.getAddress(obj.zipCode())).thenReturn(dto);
        when(repository.save(any(Address.class))).thenAnswer(invocation -> invocation.getArgument(0));

        Address address = register.execute(obj);

        assertEquals(dto.cep(), address.getZipCode());
    }

    @Test
    @DisplayName("address already registered")
    void registerDataConflict() {
        AddressRequestDTO dto = newAddressRequestDTO();
        Address address = newAddress();

        when(repository.findByNumberAndZipCode(dto.number(), dto.zipCode())).thenReturn(Optional.of(address));

        DataConflictException exception = assertThrows(DataConflictException.class, () -> register.execute(dto));

        assertEquals(ADDRESS_ALREADY_REGISTERED, exception.getMessage());
    }
}