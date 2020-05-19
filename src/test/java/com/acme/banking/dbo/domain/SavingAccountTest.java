package com.acme.banking.dbo.domain;

import org.junit.Test;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

public class SavingAccountTest {
    private static final double DUMMY_AMOUNT = 0;
    private static final UUID STUB_ID = UUID.randomUUID();
    private static final UUID STUB_CLIENT_ID = UUID.randomUUID();
    private static final Client STUB_CLIENT = new Client(STUB_CLIENT_ID, "client name");

    @Test(expected = IllegalArgumentException.class)
    public void shouldNotCreateWhenIdIsNull() {
        new SavingAccount(null, STUB_CLIENT, DUMMY_AMOUNT);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldNotCreateWhenClienIsNull() {
        new SavingAccount(STUB_ID, null, DUMMY_AMOUNT);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldNotCreateWhenAmountIsLessThanZero() {
        final double dummyNegativeAmount = -1;

        new SavingAccount(STUB_ID, STUB_CLIENT, dummyNegativeAmount);
    }

    @Test
    public void shouldSavePropertiesWhenCreated() {
        final SavingAccount sut = new SavingAccount(STUB_ID, STUB_CLIENT, DUMMY_AMOUNT);

        assertThat(sut.getId()).isNotNull().isEqualTo(STUB_ID);
        assertThat(sut.getClient()).isNotNull().isEqualTo(STUB_CLIENT);
        assertThat(sut.getClientId()).isNotNull().isEqualTo(STUB_CLIENT_ID);
        assertThat(sut.getAmount()).isEqualTo(DUMMY_AMOUNT);
    }
}