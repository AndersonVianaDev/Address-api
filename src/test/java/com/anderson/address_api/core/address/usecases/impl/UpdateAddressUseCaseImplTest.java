package com.anderson.address_api.core.address.usecases.impl;

import com.anderson.address_api.core.address.dataprovider.AddressRepository;
import com.anderson.address_api.core.address.domain.Address;
import com.anderson.address_api.core.address.dtos.AddressUpdateDTO;
import com.anderson.address_api.core.address.usecases.ports.FindAddressByIdUseCasePort;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.UUID;

import static com.anderson.address_api.core.address.usecases.builders.AddressTestUnitBuilder.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UpdateAddressUseCaseImplTest {

    @Mock
    FindAddressByIdUseCasePort findAddressById;

    @Mock
    AddressRepository repository;

    @InjectMocks
    UpdateAddressUseCaseImpl useCase;

    @Test
    @DisplayName("updating address successfully")
    void updateSuccessfully() {
        Address address = newAddress();
        UUID id = address.getId();

        AddressUpdateDTO dto = newUpdateDTO();

        when(findAddressById.execute(id)).thenReturn(address);
        when(repository.update(any(Address.class))).thenAnswer(invocation -> invocation.getArgument(0));

        Address result = useCase.execute(id, dto);

        assertEquals(dto.complement(), result.getComplement());
        assertEquals(dto.number(), result.getNumber());
    }

    @Test
    @DisplayName("updating address successfully but not updating the complement")
    void updateSuccessfullyButNotUpdatingTheComplement() {
        Address address = newAddress();
        UUID id = address.getId();

        AddressUpdateDTO dto = newUpdateDTOComplementNULL();

        when(findAddressById.execute(id)).thenReturn(address);
        when(repository.update(any(Address.class))).thenAnswer(invocation -> invocation.getArgument(0));

        Address result = useCase.execute(id, dto);

        assertNotEquals(dto.complement(), result.getComplement());
        assertEquals(dto.number(), result.getNumber());
    }

    @Test
    @DisplayName("updating address successfully but not updating the number")
    void updateSuccessfullyButNotUpdatingTheNumber() {
        Address address = newAddress();
        UUID id = address.getId();

        AddressUpdateDTO dto = newUpdateDTONumberNULL();

        when(findAddressById.execute(id)).thenReturn(address);
        when(repository.update(any(Address.class))).thenAnswer(invocation -> invocation.getArgument(0));

        Address result = useCase.execute(id, dto);

        assertEquals(dto.complement(), result.getComplement());
        assertNotEquals(dto.number(), result.getNumber());
    }
}