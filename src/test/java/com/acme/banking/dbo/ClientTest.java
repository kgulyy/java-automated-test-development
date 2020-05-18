package com.acme.banking.dbo;

import com.acme.banking.dbo.domain.Client;
import org.junit.Before;
import org.junit.Test;

import java.util.UUID;

import static org.hamcrest.CoreMatchers.allOf;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;

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
        //region when
        final Client sut = new Client(stubId, DUMMY_CLIENT_NAME);
        //endregion

        //region then
        assertThat(sut.getId(),
                allOf(
                        equalTo(stubId),
                        notNullValue()
                ));

        assertThat(sut.getName(),
                allOf(
                        equalTo(DUMMY_CLIENT_NAME),
                        notNullValue()
                ));
        //endregion
    }
}
