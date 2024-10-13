package com.anderson.address_api.entrypoint.controllers;

import com.anderson.address_api.core.address.domain.Address;
import com.anderson.address_api.dataprovider.address.dataprovider.repositories.port.SpringAddressRepository;
import com.anderson.address_api.dataprovider.address.entity.AddressEntity;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
public class FindById {

    @Autowired
    private MockMvc mvc;
    @Autowired
    private ObjectMapper mapper;

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
    @DisplayName("")
    void findByIdSuccessfully() throws Exception {
        final AddressEntity address = repository.save(new AddressEntity(null, "24416060", "São Gonçalo", "RJ", "Barro Vermelho", "aleatorio", "345", null, null));
        final UUID id = address.getId();

        final MvcResult result = mvc.perform(MockMvcRequestBuilders.get("/get/" + id))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();

        final String content = result.getResponse().getContentAsString();

        final Address addressResult = mapper.readValue(content, Address.class);

        assertEquals(id, addressResult.getId());
        assertNotNull(addressResult);
    }

    @Test
    @DisplayName("Should throw NotFoundException")
    void shouldThrowNotFoundException() throws Exception {
        final UUID id = UUID.randomUUID();

        mvc.perform(MockMvcRequestBuilders.get("/get/" + id))
                .andExpect(MockMvcResultMatchers.status().isNotFound())
                .andDo(MockMvcResultHandlers.print());
    }
}
