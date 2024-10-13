package com.anderson.address_api.entrypoint.controllers;

import com.anderson.address_api.core.address.dataprovider.AddressRepository;
import com.anderson.address_api.dataprovider.address.dataprovider.repositories.port.SpringAddressRepository;
import com.anderson.address_api.dataprovider.address.entity.AddressEntity;
import com.anderson.address_api.entrypoint.address.dtos.AddressValidationDTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static com.anderson.address_api.entrypoint.builder.AddressTestIntBuilder.newAddressValidationDTO;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.doThrow;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
public class RegisterAddress {

    @Autowired
    private ObjectMapper mapper;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private SpringAddressRepository repository;

    @BeforeEach
    void setup() {
        repository.deleteAll();
    }

    @AfterEach
    void cleanup() {
        repository.deleteAll();
    }

    @Test
    @DisplayName("registering address successfully")
    void registeringSuccessfully() throws Exception{
        final AddressValidationDTO request = newAddressValidationDTO();

        final String requestAsString = mapper.writeValueAsString(request);

        final MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestAsString))
                .andExpect(status().isCreated())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();

        final String content = result.getResponse().getContentAsString();

        final AddressEntity address = mapper.readValue(content, AddressEntity.class);

        assertNotNull(address.getId());
        assertEquals(request.complement(), address.getComplement());
        assertEquals(request.number(), address.getNumber());
        assertEquals("RJ", address.getUf());
    }

    @Test
    @DisplayName("Should throw DataConflictException when address with same number and zip code already exists")
    void shouldThrowDataConflictExceptionWhenAddressAlreadyRegistered() throws Exception {
        final AddressValidationDTO request = newAddressValidationDTO();
        repository.save(new AddressEntity(null, request.zipCode(), "São Gonçalo", "RJ", "Barro Vermelho", "aleatorio", "345", null, null));

        final String requestAsString = mapper.writeValueAsString(request);

        mockMvc.perform(MockMvcRequestBuilders.post("/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestAsString))
                .andExpect(status().isConflict())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
    }
}
