package com.waucher.waucher.Controllers.IssueController.Dto;

import com.waucher.waucher.DAL.Enums.IssueStatus;

public class GetIssueResponse {

    private String descriprtion;

    public String getDescriprtion() {
        return descriprtion;
    }

    private Double amount;

    public Double getAmount() {
        return amount;
    }

    private Boolean isValid;

    public Boolean isValid() {
        return isValid;
    }

    private String barCode;

    public String getBarCode() {
        return barCode;
    }

    private IssueStatus issueStatus;

    public IssueStatus getIssueStatus() {
        return issueStatus;
    }

    public GetIssueResponse(String descriprtion, Double amount, Boolean isValid, String barCode,
            IssueStatus issueStatus) {
        this.descriprtion = descriprtion;
        this.amount = amount;
        this.isValid = isValid;
        this.barCode = barCode;
        this.issueStatus = issueStatus;
    }
}
