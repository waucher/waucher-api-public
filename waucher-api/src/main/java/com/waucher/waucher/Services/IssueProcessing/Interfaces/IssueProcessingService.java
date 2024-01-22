package com.waucher.waucher.Services.IssueProcessing.Interfaces;

import com.waucher.waucher.Controllers.IssueController.Dto.CreateIssueRequest;
import com.waucher.waucher.Controllers.IssueController.Dto.GetIssueResponse;
import com.waucher.waucher.Controllers.IssueController.Dto.GetUserIssueResponse;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public interface IssueProcessingService {

    public UUID createNewIssue(UUID userId, CreateIssueRequest request);

    public void processIssue(UUID issueId);

    public boolean deleteIssue(UUID issueId);

    public void completeIssue(UUID issueId);

    public List<GetUserIssueResponse> getIssuesByEmployeeId(UUID userId);

    public void declineIssue(UUID issueId, String reason);

    public GetIssueResponse getIssueById(UUID issueId);

    List<GetIssueResponse> getAllCompletedIssues();

}
