package com.acme.banking.dbo.domain;

import com.acme.banking.dbo.builder.ClientStubBuilder;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.extension.example.random.Random;
import org.junit.jupiter.api.extension.example.random.RandomParametersExtension;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class SavingAccountTest {
    private static final double DUMMY_AMOUNT = 0;
    private static final UUID DUMMY_ID = UUID.randomUUID();
    private static final UUID DUMMY_CLIENT_ID = UUID.randomUUID();
    private static final Client STUB_CLIENT = new ClientStubBuilder()
            .withId(DUMMY_CLIENT_ID).withName("client name").build();

    @Test
    void shouldNotCreateWhenIdIsNull() {
        assertThrows(IllegalArgumentException.class,
                () -> new SavingAccount(null, STUB_CLIENT, DUMMY_AMOUNT));
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
                () -> new SavingAccount(DUMMY_ID, STUB_CLIENT, dummyNegativeAmount));
    }

    @Test
    @ExtendWith(RandomParametersExtension.class)
    void shouldSavePropertiesWhenCreated(@Random double dummyAmount) {
        final SavingAccount sut = new SavingAccount(DUMMY_ID, STUB_CLIENT, dummyAmount);

        assertThat(sut.getId()).isNotNull().isEqualTo(DUMMY_ID);
        assertThat(sut.getClient()).isNotNull().isEqualTo(STUB_CLIENT);
        assertThat(sut.getClientId()).isNotNull().isSameAs(DUMMY_CLIENT_ID);
        assertThat(sut.getAmount()).isEqualTo(dummyAmount);
    }
}