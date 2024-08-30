package com.anderson.address_api.entrypoint.address.controller;

import com.anderson.address_api.core.address.domain.Address;
import com.anderson.address_api.entrypoint.address.dtos.AddressUpdateValidationDTO;
import com.anderson.address_api.entrypoint.address.dtos.AddressValidationDTO;
import com.anderson.address_api.entrypoint.address.proxy.AddressProxyService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.UUID;

@RestController
@RequestMapping(name = "/api/address")
public class AddressController {

    private final AddressProxyService proxy;

    @Autowired
    public AddressController(AddressProxyService proxy) {
        this.proxy = proxy;
    }

    @PostMapping("/register")
    public ResponseEntity<Address> register(@Valid @RequestBody AddressValidationDTO dto) {
        Address address = proxy.register(dto);
        return ResponseEntity.created(ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(address.getId())
                .toUri())
                .body(address);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<Address> findById(@PathVariable UUID id) {
        Address address = proxy.findById(id);
        return ResponseEntity.ok(address);
    }

    @PutMapping("/put/{id}")
    public ResponseEntity<Address> put(@PathVariable UUID id, @Valid @RequestBody AddressUpdateValidationDTO dto) {
        Address address = proxy.update(id, dto);
        return ResponseEntity.ok(address);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        proxy.delete(id);
        return ResponseEntity.noContent().build();
    }
}
