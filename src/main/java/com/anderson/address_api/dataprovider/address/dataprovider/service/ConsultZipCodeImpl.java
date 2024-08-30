package com.anderson.address_api.dataprovider.address.dataprovider.service;

import com.anderson.address_api.core.address.dataprovider.ConsultZipCode;
import com.anderson.address_api.core.address.dtos.AddressExternalDTO;
import com.anderson.address_api.core.exceptions.NotFoundException;
import com.anderson.address_api.dataprovider.address.dataprovider.client.ViaCep;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;

import static com.anderson.address_api.core.exceptions.constants.ExceptionConstants.INTERNAL_SERVER_ERROR;
import static com.anderson.address_api.core.exceptions.constants.ExceptionConstants.ZIP_CODE_DOES_NOT_EXIST;
import static java.util.Objects.isNull;

@Component
public class ConsultZipCodeImpl implements ConsultZipCode {

    private final ViaCep viaCep;

    private final ObjectMapper mapper;

    private final Jedis jedis;

    private static final int EXPIRATION_TIME = 24 * 60 * 60;

    @Autowired
    public ConsultZipCodeImpl(ViaCep viaCep, ObjectMapper mapper, Jedis jedis) {
        this.viaCep = viaCep;
        this.mapper = mapper;
        this.jedis = jedis;
    }

    @Override
    public AddressExternalDTO getAddress(String zipCode) {
        var value = jedis.get(zipCode);

        try {
            if(value != null) return mapper.readValue(value, AddressExternalDTO.class);
            AddressExternalDTO obj = viaCep.getAddress(zipCode);

            if(isNull(obj.cep())) throw new NotFoundException(ZIP_CODE_DOES_NOT_EXIST);

            jedis.setex(zipCode, EXPIRATION_TIME, mapper.writeValueAsString(obj));

            return obj;
        } catch (NotFoundException e) {
            throw e;
        } catch (Exception e) {
            throw new RuntimeException(INTERNAL_SERVER_ERROR);
        }
    }
}
