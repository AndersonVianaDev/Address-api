package com.anderson.address_api.core.address.dataprovider;

import com.anderson.address_api.core.address.dtos.AddressExternalDTO;

public interface ConsultZipCode {
    AddressExternalDTO getAddress(String zipCode);
}
