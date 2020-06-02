package com.acme.banking.dbo.builder;

import com.acme.banking.dbo.domain.Client;

import java.util.UUID;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ClientStubBuilder {
    private final Client clientStub = mock(Client.class);

    public Client build() {
        return clientStub;
    }

    public ClientStubBuilder withRandomId() {
        when(clientStub.getId()).thenReturn(UUID.randomUUID());
        return this;
    }

    public ClientStubBuilder withId(UUID id) {
        when(clientStub.getId()).thenReturn(id);
        return this;
    }

    public ClientStubBuilder withName(String name) {
        when(clientStub.getName()).thenReturn(name);
        return this;
    }
}
