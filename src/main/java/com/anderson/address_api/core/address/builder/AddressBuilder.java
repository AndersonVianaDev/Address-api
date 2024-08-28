package com.anderson.address_api.core.address.builder;

import com.anderson.address_api.core.address.domain.Address;

import java.time.Instant;
import java.util.UUID;

public class AddressBuilder {

    private UUID id;
    private String zipCode;
    private String locality;
    private String uf;
    private String neighborhood;
    private String complement;
    private String number;
    private Instant createdAt;
    private Instant updatedAt;

    public AddressBuilder withId(UUID id) {
        this.id = id;
        return this;
    }

    public AddressBuilder withZipCode(String zipCode) {
        this.zipCode = zipCode;
        return this;
    }

    public AddressBuilder withLocality(String locality) {
        this.locality = locality;
        return this;
    }

    public AddressBuilder withUf(String uf) {
        this.uf = uf;
        return this;
    }

    public AddressBuilder withNeighborhood(String neighborhood) {
        this.neighborhood = neighborhood;
        return this;
    }

    public AddressBuilder withComplement(String complement) {
        this.complement = complement;
        return this;
    }

    public AddressBuilder withNumber(String number) {
        this.number = number;
        return this;
    }

    public AddressBuilder withCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
        return this;
    }

    public AddressBuilder withUpdatedAt(Instant updatedAt) {
        this.updatedAt = updatedAt;
        return this;
    }

    public Address build() {
        return new Address(id, zipCode, locality, uf, neighborhood, complement, number, createdAt, updatedAt);
    }
}
