package com.acme.banking.dbo.domain;

import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class SavingAccountTest {
    private static final double DUMMY_AMOUNT = 0;
    private static final UUID DUMMY_ID = UUID.randomUUID();
    private static final UUID DUMMY_CLIENT_ID = UUID.randomUUID();
    private static final Client DUMMY_CLIENT = new Client(DUMMY_CLIENT_ID, "client name");

    @Test
    void shouldNotCreateWhenIdIsNull() {
        assertThrows(IllegalArgumentException.class,
                () -> new SavingAccount(null, DUMMY_CLIENT, DUMMY_AMOUNT));
    }

    @Test
    void shouldNotCreateWhenClienIsNull() {
        assertThrows(IllegalArgumentException.class,
                () -> new SavingAccount(DUMMY_ID, null, DUMMY_AMOUNT));
    }

    @Test
    void shouldNotCreateWhenAmountIsLessThanZero() {
        final double dummyNegativeAmount = -1;

        assertThrows(IllegalArgumentException.class,
                () -> new SavingAccount(DUMMY_ID, DUMMY_CLIENT, dummyNegativeAmount));
    }

    @Test
    void shouldSavePropertiesWhenCreated() {
        final SavingAccount sut = new SavingAccount(DUMMY_ID, DUMMY_CLIENT, DUMMY_AMOUNT);

        assertThat(sut.getId()).isNotNull().isEqualTo(DUMMY_ID);
        assertThat(sut.getClient()).isNotNull().isEqualTo(DUMMY_CLIENT);
        assertThat(sut.getClientId()).isNotNull().isSameAs(DUMMY_CLIENT_ID);
        assertThat(sut.getAmount()).isEqualTo(DUMMY_AMOUNT);
    }
}