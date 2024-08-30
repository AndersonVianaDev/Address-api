package com.anderson.address_api.entrypoint.address.proxy;

import com.anderson.address_api.core.address.domain.Address;
import com.anderson.address_api.core.address.dtos.AddressRequestDTO;
import com.anderson.address_api.core.address.dtos.AddressUpdateDTO;
import com.anderson.address_api.core.address.usecases.ports.DeleteAddressByIdUseCasePort;
import com.anderson.address_api.core.address.usecases.ports.FindAddressByIdUseCasePort;
import com.anderson.address_api.core.address.usecases.ports.RegisterAddressUseCasePort;
import com.anderson.address_api.core.address.usecases.ports.UpdateAddressUseCasePort;
import com.anderson.address_api.entrypoint.address.dtos.AddressUpdateValidationDTO;
import com.anderson.address_api.entrypoint.address.dtos.AddressValidationDTO;
import org.springframework.stereotype.Service;

import java.util.UUID;

import static com.anderson.address_api.entrypoint.address.mapper.AddressMapper.toAddressRequestDTO;
import static com.anderson.address_api.entrypoint.address.mapper.AddressMapper.toAddressUpdateDTO;

@Service
public class AddressProxyService {

    private final RegisterAddressUseCasePort registerAddressUseCase;
    private final FindAddressByIdUseCasePort findAddressByIdUseCase;
    private final UpdateAddressUseCasePort updateAddressUseCase;
    private final DeleteAddressByIdUseCasePort deleteAddressByIdUseCase;

    public AddressProxyService(RegisterAddressUseCasePort registerAddressUseCase, FindAddressByIdUseCasePort findAddressByIdUseCase, UpdateAddressUseCasePort updateAddressUseCase, DeleteAddressByIdUseCasePort deleteAddressByIdUseCase) {
        this.registerAddressUseCase = registerAddressUseCase;
        this.findAddressByIdUseCase = findAddressByIdUseCase;
        this.updateAddressUseCase = updateAddressUseCase;
        this.deleteAddressByIdUseCase = deleteAddressByIdUseCase;
    }

    public Address register(AddressValidationDTO request) {
        AddressRequestDTO dto = toAddressRequestDTO(request);
        return registerAddressUseCase.execute(dto);
    }

    public Address findById(UUID id) {
        return findAddressByIdUseCase.execute(id);
    }

    public Address update(UUID id, AddressUpdateValidationDTO request) {
        AddressUpdateDTO dto = toAddressUpdateDTO(request);
        return updateAddressUseCase.execute(id, dto);
    }

    public void delete(UUID id) {
        deleteAddressByIdUseCase.execute(id);
    }
}
