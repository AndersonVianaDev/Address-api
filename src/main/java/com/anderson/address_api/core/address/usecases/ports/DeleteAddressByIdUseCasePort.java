package com.anderson.address_api.core.address.usecases.ports;

import java.util.UUID;

public interface DeleteAddressByIdUseCasePort {
    void execute(UUID id);
}
