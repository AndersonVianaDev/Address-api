package com.anderson.address_api.dataprovider.address.builder;

import com.anderson.address_api.dataprovider.address.entity.AddressEntity;

import java.time.Instant;
import java.util.UUID;

public class AddressEntityBuilder {
    private UUID id;
    private String zipCode;
    private String locality;
    private String uf;
    private String neighborhood;
    private String complement;
    private String number;
    private Instant createdAt;
    private Instant updatedAt;

    public AddressEntityBuilder withId(UUID id) {
        this.id = id;
        return this;
    }

    public AddressEntityBuilder withZipCode(String zipCode) {
        this.zipCode = zipCode;
        return this;
    }

    public AddressEntityBuilder withLocality(String locality) {
        this.locality = locality;
        return this;
    }

    public AddressEntityBuilder withUf(String uf) {
        this.uf = uf;
        return this;
    }

    public AddressEntityBuilder withNeighborhood(String neighborhood) {
        this.neighborhood = neighborhood;
        return this;
    }

    public AddressEntityBuilder withComplement(String complement) {
        this.complement = complement;
        return this;
    }

    public AddressEntityBuilder withNumber(String number) {
        this.number = number;
        return this;
    }

    public AddressEntityBuilder withCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
        return this;
    }

    public AddressEntityBuilder withUpdatedAt(Instant updatedAt) {
        this.updatedAt = updatedAt;
        return this;
    }

    public AddressEntity build() {
        return new AddressEntity(id, zipCode, locality, uf, neighborhood, complement, number, createdAt, updatedAt);
    }
}
