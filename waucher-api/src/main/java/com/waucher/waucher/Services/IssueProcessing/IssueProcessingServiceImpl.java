package com.waucher.waucher.Services.IssueProcessing;

import com.waucher.waucher.Controllers.IssueController.Dto.CreateIssueRequest;
import com.waucher.waucher.Controllers.IssueController.Dto.GetIssueResponse;
import com.waucher.waucher.Controllers.IssueController.Dto.GetUserIssueResponse;
import com.waucher.waucher.DAL.Enums.IssueStatus;
import com.waucher.waucher.DAL.Models.Issue;
import com.waucher.waucher.DAL.Models.Journal;
import com.waucher.waucher.DAL.Repositories.IssueRepository;
import com.waucher.waucher.DAL.Repositories.JournalRepository;
import com.waucher.waucher.DAL.Repositories.EmployeeRepository;
import com.waucher.waucher.MessagesLayer.MessageSender;
import com.waucher.waucher.MessagesLayer.Messages.FnsServiceMessage;
import com.waucher.waucher.Services.IssueProcessing.Interfaces.IssueProcessingService;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class IssueProcessingServiceImpl implements IssueProcessingService {

    private final IssueRepository issueRepo;

    private final EmployeeRepository employeeRepo;

    private final JournalRepository journalRepository;

    private final MessageSender sender;

    public IssueProcessingServiceImpl(IssueRepository issueRepo, EmployeeRepository employeeRepo, JournalRepository journalRepository, MessageSender sender) {
        this.issueRepo = issueRepo;
        this.employeeRepo = employeeRepo;
        this.journalRepository = journalRepository;
        this.sender = sender;
    }

    @Override
    public UUID createNewIssue(UUID userId, CreateIssueRequest request) {
        var user = employeeRepo.findById(userId).get();
        var newIssue = new Issue(user, new Date(),request.getBarCode());
        var issue = issueRepo.save(newIssue);
        var note = new Journal(new Date(), issue, issue.getStatus());
        journalRepository.save(note);
        processIssue(issue.getId());
        return issue.getId();
    }

    @Override
    public void processIssue(UUID issueId) {
        var issue = issueRepo.findById(issueId).get();
        var oldStatus = issue.getStatus();
        sender.SendNumberMessage(new FnsServiceMessage(issue.getEmployee().getId(), issue.getId(), issue.getBarCode()));
        issue.setStatus(IssueStatus.Processing);
        var note = new Journal(new Date(), issue, issue.getStatus());
        note.setPreviousIssueStatus(oldStatus);
        issueRepo.save(issue);
        journalRepository.save(note);
    }

    @Override
    public void completeIssue(UUID issueId) {
        var issue = issueRepo.findById(issueId).get();
        var oldStatus = issue.getStatus();
        issue.setStatus(IssueStatus.Done);
        var note = new Journal(new Date(), issue, issue.getStatus());
        note.setPreviousIssueStatus(oldStatus);
        issueRepo.save(issue);
        // journalRepository.save(note); fix me
    }

    @Override
    public List<GetUserIssueResponse> getIssuesByEmployeeId(UUID employeeId) {
        var issues = issueRepo.findAllByEmployeeId(employeeId).stream().map(issue ->
            new GetUserIssueResponse(
                    issue.getId(),
             issue.getAmount(),
              issue.getIsCheckValid(),
               issue.getStatus())
        ).toList();
        return issues;
    }

    @Override
    public void declineIssue(UUID issueId, String reason){
        var issue = issueRepo.findById(issueId).get();
        issue.setStatus(IssueStatus.Declined);
        issue.setCheckValid(false);
        issue.setDescription(reason);
        issueRepo.save(issue);
    }

    @Override
    public GetIssueResponse getIssueById(UUID issueId) {
        var issue = issueRepo.findById(issueId).get();
        return new GetIssueResponse(
            issue.getDescription(),
            issue.getAmount(),
             issue.getIsCheckValid(),
              issue.getBarCode(),
              issue.getStatus());
    }

    @Override
    public List<GetIssueResponse> getAllCompletedIssues() {
        var issues = issueRepo.findAllByStatus(IssueStatus.Done);
        var response = issues.stream().map(issue -> new GetIssueResponse(
                issue.getDescription(),
                issue.getAmount(),
                issue.getIsCheckValid(),
                issue.getBarCode(),
                issue.getStatus()
        )).toList();
        return response;
    }

    @Override
    public boolean deleteIssue(UUID issueId) {
        issueRepo.deleteById(issueId);
        return true;
    }
}
