package com.waucher.waucher.DAL.Models;

import com.waucher.waucher.DAL.Enums.IssueStatus;
import jakarta.persistence.*;
import org.hibernate.annotations.UuidGenerator;

import java.util.Date;
import java.util.UUID;

@Entity
public class Journal {

    public Journal(){

    }

    public Journal(Date changesDate, Issue issue, IssueStatus currentIssueStatus){
        this.changesDate = changesDate;
        this.issue = issue;
        this.currentIssueStatus = currentIssueStatus;
    }

    @Id
    @UuidGenerator
    private UUID id;

    @Column(nullable = false)
    private Date changesDate;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="issue_id", nullable = false)
    private Issue issue;

    private IssueStatus previousIssueStatus = null;

    @Column(nullable = false)
    private IssueStatus currentIssueStatus;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Date getChangesDate() {
        return changesDate;
    }

    public void setChangesDate(Date changesDate) {
        this.changesDate = changesDate;
    }

    public IssueStatus getPreviousIssueStatus() {
        return previousIssueStatus;
    }

    public void setPreviousIssueStatus(IssueStatus previousIssueStatus) {
        this.previousIssueStatus = previousIssueStatus;
    }

    public IssueStatus getCurrentIssueStatus() {
        return currentIssueStatus;
    }

    public void setCurrentIssueStatus(IssueStatus currentIssueStatus) {
        this.currentIssueStatus = currentIssueStatus;
    }

    public Issue getIssue() {
        return issue;
    }

    public void setIssue(Issue issue) {
        this.issue = issue;
    }
}
