package com.waucher.waucher.Controllers.OrganizationController.Dto;

import java.util.UUID;

public class GetValidOrganizationResponse {
    private UUID id;

    private String inn;

    private String name;

    public GetValidOrganizationResponse(UUID id, String inn, String name) {
        this.id = id;
        this.inn = inn;
        this.name = name;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
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
}
