package com.anderson.address_api.core.address.domain;

import java.time.Instant;
import java.util.UUID;

public class Address {

    private UUID id;
    private String zipCode;
    private String locality;
    private String uf;
    private String neighborhood;
    private String complement;
    private String number;
    private Instant createdAt;
    private Instant updatedAt;

    private Address() {
    }

    public Address(UUID id, String zipCode, String locality, String uf, String neighborhood, String complement, String number, Instant createdAt, Instant updatedAt) {
        this.id = id;
        this.zipCode = zipCode;
        this.locality = locality;
        this.uf = uf;
        this.neighborhood = neighborhood;
        this.complement = complement;
        this.number = number;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public UUID getId() {
        return id;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getLocality() {
        return locality;
    }

    public void setLocality(String locality) {
        this.locality = locality;
    }

    public String getUf() {
        return uf;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }

    public String getNeighborhood() {
        return neighborhood;
    }

    public void setNeighborhood(String neighborhood) {
        this.neighborhood = neighborhood;
    }

    public String getComplement() {
        return complement;
    }

    public void setComplement(String complement) {
        this.complement = complement;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public Instant getUpdatedAt() {
        return updatedAt;
    }
}
