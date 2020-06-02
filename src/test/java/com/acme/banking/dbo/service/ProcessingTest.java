package com.acme.banking.dbo.service;

import com.acme.banking.dbo.builder.ClientRepositoryStubBuilder;
import com.acme.banking.dbo.builder.ClientStubBuilder;
import com.acme.banking.dbo.dao.ClientRepository;
import com.acme.banking.dbo.domain.Client;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

class ProcessingTest {
    private static final String DUMMY_CLIENT_NAME = "dummy client name";

    @Test
    void shouldNotCreateClientWhenNameIsNull() {
        final ClientRepository clientRepoStub = new ClientRepositoryStubBuilder().build();
        final Processing sut = new Processing(clientRepoStub);

        assertThrows(IllegalArgumentException.class, () -> sut.createClient(null));
    }

    @Test
    void shouldNotCreateClientWhenNameIsEmpty() {
        final ClientRepository clientRepoStub = new ClientRepositoryStubBuilder().build();
        final Processing sut = new Processing(clientRepoStub);

        assertThrows(IllegalArgumentException.class, () -> sut.createClient(""));
    }

    @Test
    void shouldClientIdIsNotNullWhenClientCreated() {
        final Client clientStub = new ClientStubBuilder().withRandomId().build();
        final ClientRepository clientRepoStub = new ClientRepositoryStubBuilder()
                .withClient(clientStub)
                .build();
        final Processing sut = new Processing(clientRepoStub);

        final UUID clientId = sut.createClient(DUMMY_CLIENT_NAME);

        assertThat(clientId).isNotNull();
    }

    @Test
    void shouldSaveClientInRepoWhenClientCreated() {
        final Client clientStub = new ClientStubBuilder().withRandomId().build();
        final ClientRepository clientRepoStub = new ClientRepositoryStubBuilder()
                .withClient(clientStub)
                .build();
        final Processing sut = new Processing(clientRepoStub);

        sut.createClient(DUMMY_CLIENT_NAME);

        verify(clientRepoStub, times(1)).save(any());
    }

    @Test
    void shouldGetClientIdAfterSavingWhenClientCreated() {
        final Client clientStub = new ClientStubBuilder().withRandomId().build();
        final ClientRepository clientRepoStub = new ClientRepositoryStubBuilder()
                .withClient(clientStub)
                .build();
        final Processing sut = new Processing(clientRepoStub);

        sut.createClient(DUMMY_CLIENT_NAME);

        //noinspection ResultOfMethodCallIgnored
        verify(clientStub, times(1)).getId();
    }
}