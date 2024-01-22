package com.waucher.waucher.Controllers.IssueController.Dto;

public class CreateIssueRequest {

    private String barCode;

    public CreateIssueRequest(String barCode, String description) {
        this.barCode = barCode;
    }

    public String getBarCode() {
        return barCode;
    }

    public void setBarCode(String barCode) {
        this.barCode = barCode;
    }
}
