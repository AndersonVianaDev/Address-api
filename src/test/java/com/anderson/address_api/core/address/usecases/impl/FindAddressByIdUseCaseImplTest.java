package com.anderson.address_api.core.address.usecases.impl;

import com.anderson.address_api.core.address.dataprovider.AddressRepository;
import com.anderson.address_api.core.address.domain.Address;
import com.anderson.address_api.core.exceptions.NotFoundException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.UUID;

import static com.anderson.address_api.core.address.usecases.builders.AddressTestUnitBuilder.newAddress;
import static com.anderson.address_api.core.exceptions.constants.ExceptionConstants.ADDRESS_NOT_FOUND;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class FindAddressByIdUseCaseImplTest {

    @InjectMocks
    private FindAddressByIdUseCaseImpl findAddressById;

    @Mock
    private AddressRepository repository;

    @Test
    @DisplayName("successfully finding address by id")
    void findByIdSuccessfully() {
        Address address = newAddress();
        UUID id = address.getId();

        when(repository.findById(id)).thenReturn(Optional.of(address));

        Address addressResult = findAddressById.execute(id);

        assertEquals(address, addressResult);
    }

    @Test
    @DisplayName("address not found")
    void findByIdNotFound() {
        UUID id = UUID.randomUUID();

        when(repository.findById(id)).thenReturn(Optional.empty());

        NotFoundException exception = assertThrows(NotFoundException.class, () -> findAddressById.execute(id));

        assertEquals(ADDRESS_NOT_FOUND, exception.getMessage());
    }
}
