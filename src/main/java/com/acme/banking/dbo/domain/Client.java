package com.acme.banking.dbo.domain;

import java.util.ArrayList;
import java.util.Collection;
import java.util.UUID;

public class Client {
    private final UUID id;
    private final String name;
    private final Collection<UUID> accountIds = new ArrayList<>(); //TODO

    public Client(UUID id, String name) {
        if (id == null) throw new IllegalArgumentException("Id not be null");
        if (name == null || name.isEmpty()) throw new IllegalArgumentException("Name not be null or empty");

        this.id = id;
        this.name = name;
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    // TODO: add equals() and hashCode()
}
