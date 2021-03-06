package com.acme.banking.dbo.domain;

import java.util.UUID;

public class SavingAccount implements Account {
    private final UUID id;
    private final Client client;
    private final double amount;

    public SavingAccount(UUID id, Client client, double amount) {
        if (id == null) throw new IllegalArgumentException("Id not be null");
        if (client == null) throw new IllegalArgumentException("Client not be null");
        if (amount < 0) throw new IllegalArgumentException("Amount must be not less than 0");

        this.id = id;
        this.client = client;
        this.amount = amount;
    }

    public Client getClient() {
        return client;
    }

    public double getAmount() {
        return amount;
    }

    @Override
    public UUID getId() {
        return id;
    }

    @Override
    public UUID getClientId() {
        return client.getId();
    }
}
