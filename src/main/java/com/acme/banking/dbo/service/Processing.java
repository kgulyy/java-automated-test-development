package com.acme.banking.dbo.service;

import com.acme.banking.dbo.dao.ClientRepository;
import com.acme.banking.dbo.domain.Account;
import com.acme.banking.dbo.domain.Cash;
import com.acme.banking.dbo.domain.Client;

import java.util.Collection;
import java.util.UUID;

public class Processing {
    ClientRepository repository;

    public Processing(ClientRepository repository) {
        this.repository = repository;
    }

    public UUID createClient(String name) {
        if (name == null || name.isEmpty()) throw new IllegalArgumentException("Name not be null or empty");

        final Client client = new Client(UUID.randomUUID(), name);
        final Client savedClient = repository.save(client);

        return savedClient.getId();
    }

    public Collection<Account> getAccountsByClientId(UUID clientId) {
        return null;
    }

    public void transfer(double amount, UUID fromAccountId, UUID toAccountId) {

    }

    public void cash(double amount, UUID fromAccountId) {

        Cash.log(amount, fromAccountId);
    }
}
