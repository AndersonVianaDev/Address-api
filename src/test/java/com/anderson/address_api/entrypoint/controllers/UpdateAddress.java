package com.anderson.address_api.entrypoint.controllers;

import com.anderson.address_api.core.address.domain.Address;
import com.anderson.address_api.dataprovider.address.dataprovider.repositories.port.SpringAddressRepository;
import com.anderson.address_api.dataprovider.address.entity.AddressEntity;
import com.anderson.address_api.entrypoint.address.dtos.AddressUpdateValidationDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.UUID;

import static com.anderson.address_api.entrypoint.builder.AddressTestIntBuilder.newAddressUpdateValidationDTO;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
public class UpdateAddress {

    @Autowired
    private ObjectMapper mapper;

    @Autowired
    private SpringAddressRepository repository;

    @Autowired
    private MockMvc mvc;

    @Test
    @DisplayName("address update successful")
    void updateSuccessfully() throws Exception {
        final AddressEntity address = repository.save(new AddressEntity(null, "24416060", "São Gonçalo", "RJ", "Barro Vermelho", "aleatorio", "345", null, null));
        final UUID id = address.getId();

        final AddressUpdateValidationDTO request = newAddressUpdateValidationDTO();
        final String requestAsString = mapper.writeValueAsString(request);

        MvcResult result = mvc.perform(MockMvcRequestBuilders.put("/put/" + id)
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestAsString))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();

        final String content = result.getResponse().getContentAsString();
        final Address addressResult = mapper.readValue(content, Address.class);

        assertNotNull(addressResult);
        assertEquals(request.number(), addressResult.getNumber());
        assertEquals(request.complement(), addressResult.getComplement());
    }

    @Test
    @DisplayName("Should throw Not Found Exception")
    void shouldThrowNotFoundException() throws Exception{
        final UUID id = UUID.randomUUID();
        final AddressUpdateValidationDTO request = newAddressUpdateValidationDTO();

        final String requestAsString = mapper.writeValueAsString(request);

        mvc.perform(MockMvcRequestBuilders.put("/put/" + id)
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestAsString))
                .andExpect(MockMvcResultMatchers.status().isNotFound())
                .andDo(MockMvcResultHandlers.print());
    }
}
