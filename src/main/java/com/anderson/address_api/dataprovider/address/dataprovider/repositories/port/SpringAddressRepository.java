package com.anderson.address_api.dataprovider.address.dataprovider.repositories.port;

import com.anderson.address_api.dataprovider.address.entity.AddressEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface SpringAddressRepository extends JpaRepository<AddressEntity, UUID> {
    @Query("SELECT a FROM AddressEntity a " +
            "WHERE a.number:= number " +
            "AND a.zipCode:= zipcode")
    Optional<AddressEntity> findByNumberAndZipCode(@Param("number") String number, @Param("zipCode") String zipCode);
}
