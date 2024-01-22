package com.waucher.waucher.DAL.Models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import org.hibernate.annotations.UuidGenerator;

import java.util.UUID;

@Entity
public class ValidOrganization {
    @Id
    @UuidGenerator
    private UUID id;

    private String name;

    private String inn;

    public ValidOrganization(){

    };

    public ValidOrganization(String inn, String name) {
        this.inn = inn;
        this.name = name;
    }

    public String getInn() {
        return inn;
    }

    public void setInn(String inn) {
        this.inn = inn;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }
}
