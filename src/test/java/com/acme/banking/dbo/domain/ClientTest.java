package com.acme.banking.dbo.domain;

import org.junit.Before;
import org.junit.Test;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

public class ClientTest {
    private static final String DUMMY_CLIENT_NAME = "dummy client name";
    private UUID stubId;

    @Before
    public void initStubId() {
        stubId = UUID.randomUUID();
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldNotCreateWhenIdIsNull() {
        new Client(null, DUMMY_CLIENT_NAME);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldNotCreateWhenNameIsNull() {
        new Client(stubId, null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldNotCreateWhenNameIsEmpty() {
        final String dummyEmptyName = "";

        new Client(stubId, dummyEmptyName);
    }

    @Test
    public void shouldSavePropertiesWhenCreated() {
        final Client sut = new Client(stubId, DUMMY_CLIENT_NAME);

        assertThat(sut.getId()).isNotNull().isEqualTo(stubId);
        assertThat(sut.getName()).isNotNull().isEqualTo(DUMMY_CLIENT_NAME);
    }
}
