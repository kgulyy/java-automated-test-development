package com.acme.banking.dbo.builder;

import com.acme.banking.dbo.dao.ClientRepository;
import com.acme.banking.dbo.domain.Client;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ClientRepositoryStubBuilder {
    private final ClientRepository clientRepoStub = mock(ClientRepository.class);

    public ClientRepository build() {
        return clientRepoStub;
    }

    public ClientRepositoryStubBuilder withClient(Client client) {
        when(clientRepoStub.save(any())).thenReturn(client);
        return this;
    }
}
