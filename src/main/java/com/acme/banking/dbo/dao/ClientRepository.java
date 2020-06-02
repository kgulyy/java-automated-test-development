package com.acme.banking.dbo.dao;

import com.acme.banking.dbo.domain.Client;

@SuppressWarnings("InterfaceNeverImplemented")
public interface ClientRepository {
    Client save(Client client);
}
