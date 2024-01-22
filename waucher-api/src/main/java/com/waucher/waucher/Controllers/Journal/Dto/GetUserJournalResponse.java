package com.waucher.waucher.Controllers.Journal.Dto;

import com.waucher.waucher.DAL.Enums.IssueStatus;

import java.util.Date;
import java.util.UUID;

public class GetUserJournalResponse {
    private UUID issueId;

    private IssueStatus issueStatus;

    private  IssueStatus previousStatus = null;

    private Date changesDate;

    public GetUserJournalResponse(UUID issueId, IssueStatus issueStatus, Date changesDate, IssueStatus previousStatus) {
        this.issueId = issueId;
        this.issueStatus = issueStatus;
        this.previousStatus = previousStatus;
        this.changesDate = changesDate;
    }

    public UUID getIssueId() {
        return issueId;
    }

    public void setIssueId(UUID issueId) {
        this.issueId = issueId;
    }

    public IssueStatus getIssueStatus() {
        return issueStatus;
    }

    public void setIssueStatus(IssueStatus issueStatus) {
        this.issueStatus = issueStatus;
    }

    public Date getChangesDate() {
        return changesDate;
    }

    public void setChangesDate(Date changesDate) {
        this.changesDate = changesDate;
    }

    public IssueStatus getPreviousStatus() {
        return previousStatus;
    }

    public void setPreviousStatus(IssueStatus previousStatus) {
        this.previousStatus = previousStatus;
    }
}
