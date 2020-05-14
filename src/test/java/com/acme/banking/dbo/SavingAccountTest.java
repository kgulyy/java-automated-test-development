package com.acme.banking.dbo;

import com.acme.banking.dbo.domain.Client;
import com.acme.banking.dbo.domain.SavingAccount;
import org.junit.Test;

import java.util.UUID;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.assertThat;

public class SavingAccountTest {
    private static final double DUMMY_AMOUNT = 0;
    private static final UUID STUB_ID = UUID.randomUUID();
    private static final UUID STUB_CLIENT_ID = UUID.randomUUID();
    private static final Client STUB_CLIENT = new Client(STUB_CLIENT_ID, "client name");

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowExceptionWhenIdIsNull() {

        new SavingAccount(null, STUB_CLIENT, DUMMY_AMOUNT);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowExceptionWhenClienIsNull() {
        new SavingAccount(STUB_ID, null, DUMMY_AMOUNT);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowExceptionWhenAmountIsLessThanZero() {
        final double dummyNegativeAmount = -1;

        new SavingAccount(STUB_ID, STUB_CLIENT, dummyNegativeAmount);
    }

    @Test
    public void shouldSavePropertiesWhenCreated() {
        final SavingAccount sut = new SavingAccount(STUB_ID, STUB_CLIENT, DUMMY_AMOUNT);

        assertThat(sut.getId(),
                allOf(
                        equalTo(STUB_ID),
                        notNullValue()
                ));

        assertThat(sut.getClient(),
                allOf(
                        equalTo(STUB_CLIENT),
                        notNullValue()
                ));

        assertThat(sut.getClientId(),
                allOf(
                        equalTo(STUB_CLIENT_ID),
                        notNullValue()
                ));

        assertThat(sut.getAmount(), equalTo(DUMMY_AMOUNT));
    }
}