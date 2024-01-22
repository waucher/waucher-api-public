package com.waucher.waucher.DAL.Models;

import com.waucher.waucher.DAL.Enums.IssueStatus;
import jakarta.persistence.*;
import org.hibernate.annotations.UuidGenerator;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Entity
public class Issue {

    public Issue(){

    }

    public Issue(Employee employee, Date createdAt, String barCode){
        this.employee = employee;
        this.createdAt = createdAt;
        this.barCode = barCode;
    }

    @Id
    @UuidGenerator
    private UUID id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "employee_id")
    private Employee employee;

    @Column(nullable = false)
    private Date createdAt;

    @Column(nullable = false)
    private IssueStatus status = IssueStatus.New;

    @Column(nullable = false)
    private String barCode;

    private Double amount = null;

    private String description = null;

    private String ticketId;

    public String getTicketId() {
        return ticketId;
    }

    public void setTicketId(String ticketId) {
        this.ticketId = ticketId;
    }

    private Boolean isCheckValid = false;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "issue")
    private List<Journal> journal;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public IssueStatus getStatus() {
        return status;
    }

    public void setStatus(IssueStatus status) {
        this.status = status;
    }

    public String getBarCode() {
        return barCode;
    }

    public void setBarCode(String barCode) {
        this.barCode = barCode;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getIsCheckValid() {
        return isCheckValid;
    }

    public void setCheckValid(boolean checkValid) {
        isCheckValid = checkValid;
    }

    public List<Journal> getJournal() {
        return journal;
    }

    public void setJournal(List<Journal> journal) {
        this.journal = journal;
    }
}
