package com.anderson.address_api.config.beans.address;

import com.anderson.address_api.core.address.usecases.impl.DeleteAddressByIdUseCaseImpl;
import com.anderson.address_api.core.address.usecases.impl.FindAddressByIdUseCaseImpl;
import com.anderson.address_api.core.address.usecases.impl.RegisterAddressUseCaseImpl;
import com.anderson.address_api.core.address.usecases.impl.UpdateAddressUseCaseImpl;
import com.anderson.address_api.core.address.usecases.ports.DeleteAddressByIdUseCasePort;
import com.anderson.address_api.core.address.usecases.ports.FindAddressByIdUseCasePort;
import com.anderson.address_api.core.address.usecases.ports.RegisterAddressUseCasePort;
import com.anderson.address_api.core.address.usecases.ports.UpdateAddressUseCasePort;
import com.anderson.address_api.dataprovider.address.dataprovider.repositories.impl.AddressRepositoryImpl;
import com.anderson.address_api.dataprovider.address.dataprovider.service.ConsultZipCodeImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AddressConfig {

    @Bean
    public RegisterAddressUseCasePort registerAddressUseCasePort(AddressRepositoryImpl repository, ConsultZipCodeImpl consultZipCode) {
        return new RegisterAddressUseCaseImpl(repository, consultZipCode);
    }

    @Bean
    public FindAddressByIdUseCasePort findAddressByIdUseCasePort(AddressRepositoryImpl repository) {
        return new FindAddressByIdUseCaseImpl(repository);
    }

    @Bean
    public UpdateAddressUseCasePort updateAddressUseCasePort(AddressRepositoryImpl repository, FindAddressByIdUseCasePort findAddressByIdUseCasePort) {
        return new UpdateAddressUseCaseImpl(repository, findAddressByIdUseCasePort);
    }

    @Bean
    public DeleteAddressByIdUseCasePort deleteAddressByIdUseCasePort(AddressRepositoryImpl repository, FindAddressByIdUseCasePort findAddressByIdUseCasePort) {
        return new DeleteAddressByIdUseCaseImpl(repository, findAddressByIdUseCasePort);
    }
}
