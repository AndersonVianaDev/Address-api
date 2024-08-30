package com.anderson.address_api.dataprovider.address.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.Instant;
import java.util.UUID;

@Entity
@Table(name = "tb_addresses")
public class AddressEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String zipCode;
    private String locality;
    private String uf;
    private String neighborhood;
    private String complement;
    private String number;
    @CreationTimestamp
    private Instant createdAt;
    @UpdateTimestamp
    private Instant updatedAt;

    private AddressEntity() {
    }

    public AddressEntity(UUID id, String zipCode, String locality, String uf, String neighborhood, String complement, String number, Instant createdAt, Instant updatedAt) {
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

    public String getUf() {
        return uf;
    }

    public String getNeighborhood() {
        return neighborhood;
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
