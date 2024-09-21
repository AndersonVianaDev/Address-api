package com.anderson.address_api.core.address.usecases.impl;

import com.anderson.address_api.core.address.dataprovider.AddressRepository;
import com.anderson.address_api.core.address.domain.Address;
import com.anderson.address_api.core.address.usecases.ports.FindAddressByIdUseCasePort;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.UUID;

import static com.anderson.address_api.core.address.usecases.builders.AddressTestUnitBuilder.newAddress;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class DeleteAddressByIdUseCaseImplTest {

    @Mock
    FindAddressByIdUseCasePort findAddressById;

    @Mock
    AddressRepository repository;

    @InjectMocks
    DeleteAddressByIdUseCaseImpl useCase;

    @Test
    @DisplayName("deleting address successfully")
    void deletingSuccessfully() {
        Address address = newAddress();
        UUID id = address.getId();

        when(findAddressById.execute(id)).thenReturn(address);

        useCase.execute(id);

        verify(repository, times(1)).delete(id);
    }
}