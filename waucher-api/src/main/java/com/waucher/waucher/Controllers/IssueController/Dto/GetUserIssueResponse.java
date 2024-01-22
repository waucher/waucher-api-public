package com.waucher.waucher.Controllers.IssueController.Dto;

import com.waucher.waucher.DAL.Enums.IssueStatus;

import java.util.UUID;

public class GetUserIssueResponse {
    private UUID issueId;

    private Double amount;

    private Boolean isValid;

    private IssueStatus issueStatus;

    public GetUserIssueResponse(UUID issueId, Double amount, Boolean isValid, IssueStatus issueStatus) {
        this.issueId = issueId;
        this.amount = amount;
        this.isValid = isValid;
        this.issueStatus = issueStatus;
    }

    public Double getAmount() {
        return amount;
    }

    public Boolean isValid() {
        return isValid;
    }

    public IssueStatus getIssueStatus() {
        return issueStatus;
    }

    public UUID getIssueId() {
        return issueId;
    }

    public void setIssueId(UUID issueId) {
        this.issueId = issueId;
    }
}
