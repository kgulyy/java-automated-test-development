package com.acme.banking.dbo.service;

import com.acme.banking.dbo.dao.ClientRepository;
import com.acme.banking.dbo.domain.Client;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;

class ProcessingTest {

    @Test
    void shouldNotCreateClientWhenNameIsNull() {
        final ClientRepository clientRepoStub = mock(ClientRepository.class);
        final Processing sut = new Processing(clientRepoStub);

        assertThrows(IllegalArgumentException.class, () -> sut.createClient(null));
    }

    @Test
    void shouldNotCreateClientWhenNameIsEmpty() {
        final ClientRepository clientRepoStub = mock(ClientRepository.class);
        final Processing sut = new Processing(clientRepoStub);

        assertThrows(IllegalArgumentException.class, () -> sut.createClient(""));
    }

    @Test
    void shouldClientIdIsNotNullWhenClientCreated() {
        final ClientRepository clientRepoStub = mock(ClientRepository.class);
        final Client clientStub = mock(Client.class);
        when(clientRepoStub.save(any())).thenReturn(clientStub);

        final UUID clientIdStub = UUID.randomUUID();
        when(clientStub.getId()).thenReturn(clientIdStub);

        final Processing sut = new Processing(clientRepoStub);

        final UUID clientId = sut.createClient("dummy client name");

        assertThat(clientId).isNotNull();
    }

    @Test
    void shouldSaveClientInRepoWhenClientCreated() {
        final ClientRepository clientRepoStub = mock(ClientRepository.class);
        final Client clientStub = mock(Client.class);
        when(clientRepoStub.save(any())).thenReturn(clientStub);

        final UUID clientIdStub = UUID.randomUUID();
        when(clientStub.getId()).thenReturn(clientIdStub);

        final Processing sut = new Processing(clientRepoStub);

        sut.createClient("dummy client name");

        verify(clientRepoStub, times(1)).save(any());
    }

    @Test
    void shouldGetClientIdAfterSavingWhenClientCreated() {
        final ClientRepository clientRepoStub = mock(ClientRepository.class);
        final Client clientStub = mock(Client.class);
        when(clientRepoStub.save(any())).thenReturn(clientStub);

        final UUID clientIdStub = UUID.randomUUID();
        when(clientStub.getId()).thenReturn(clientIdStub);

        final Processing sut = new Processing(clientRepoStub);

        sut.createClient("dummy client name");

        //noinspection ResultOfMethodCallIgnored
        verify(clientStub, times(1)).getId();
    }
}