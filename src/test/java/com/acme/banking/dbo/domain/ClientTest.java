package com.acme.banking.dbo.domain;

import org.junit.Test;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

public class ClientTest {
    private static final String DUMMY_CLIENT_NAME = "dummy client name";
    private static final UUID DUMMY_ID = UUID.randomUUID();

    @Test(expected = IllegalArgumentException.class)
    public void shouldNotCreateWhenIdIsNull() {
        new Client(null, DUMMY_CLIENT_NAME);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldNotCreateWhenNameIsNull() {
        new Client(DUMMY_ID, null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldNotCreateWhenNameIsEmpty() {
        final String dummyEmptyName = "";

        new Client(DUMMY_ID, dummyEmptyName);
    }

    @Test
    public void shouldSavePropertiesWhenCreated() {
        final Client sut = new Client(DUMMY_ID, DUMMY_CLIENT_NAME);

        assertThat(sut.getId()).isNotNull().isEqualTo(DUMMY_ID);
        assertThat(sut.getName()).isNotNull().isEqualTo(DUMMY_CLIENT_NAME);
    }
}
