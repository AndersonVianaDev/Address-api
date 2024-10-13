package com.anderson.address_api.entrypoint.controllers;

import com.anderson.address_api.dataprovider.address.dataprovider.repositories.port.SpringAddressRepository;
import com.anderson.address_api.dataprovider.address.entity.AddressEntity;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertFalse;

@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
public class DeleteAddress {

    @Autowired
    private SpringAddressRepository repository;

    @Autowired
    private MockMvc mvc;

    @Test
    @DisplayName("Should delete address successfully")
    void deleteSuccessfully() throws Exception {
        final AddressEntity address = repository.save(new AddressEntity(null, "24416060", "São Gonçalo", "RJ", "Barro Vermelho", "aleatorio", "345", null, null));
        final UUID id = address.getId();

        mvc.perform(MockMvcRequestBuilders.delete("/delete/" + id))
                .andExpect(MockMvcResultMatchers.status().isNoContent())
                .andDo(MockMvcResultHandlers.print());

        boolean exists = repository.findById(id).isPresent();
        assertFalse(exists, "Address should have been deleted");
    }

    @Test
    @DisplayName("Should throw not found exception")
    void shouldThrowNotFoundException() throws Exception {
        final UUID id = UUID.randomUUID();

        mvc.perform(MockMvcRequestBuilders.delete("/delete/" + id))
                .andExpect(MockMvcResultMatchers.status().isNotFound())
                .andDo(MockMvcResultHandlers.print());
    }
}
