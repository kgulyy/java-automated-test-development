package com.acme.banking.dbo.domain;

import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ClientTest {
    private static final String DUMMY_CLIENT_NAME = "dummy client name";
    private static final UUID DUMMY_ID = UUID.randomUUID();

    @Test
    void shouldNotCreateWhenIdIsNull() {
        assertThrows(IllegalArgumentException.class,
                () -> new Client(null, DUMMY_CLIENT_NAME));
    }

    @Test
    void shouldNotCreateWhenNameIsNull() {
        assertThrows(IllegalArgumentException.class,
                () -> new Client(DUMMY_ID, null));
    }

    @Test
    void shouldNotCreateWhenNameIsEmpty() {
        final String dummyEmptyName = "";

        assertThrows(IllegalArgumentException.class,
                () -> new Client(DUMMY_ID, dummyEmptyName));
    }

    @Test
    void shouldSavePropertiesWhenCreated() {
        final Client sut = new Client(DUMMY_ID, DUMMY_CLIENT_NAME);

        assertThat(sut.getId()).isNotNull().isEqualTo(DUMMY_ID);
        assertThat(sut.getName()).isNotNull().isEqualTo(DUMMY_CLIENT_NAME);
    }
}
