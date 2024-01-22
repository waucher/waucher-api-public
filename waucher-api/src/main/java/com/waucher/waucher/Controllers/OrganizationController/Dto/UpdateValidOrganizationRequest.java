package com.waucher.waucher.Controllers.OrganizationController.Dto;

public class UpdateValidOrganizationRequest {

    private String inn;

    private String name;

    public UpdateValidOrganizationRequest(String inn, String name) {
        this.inn = inn;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getInn() {
        return inn;
    }

    public void setInn(String inn) {
        this.inn = inn;
    }
}
