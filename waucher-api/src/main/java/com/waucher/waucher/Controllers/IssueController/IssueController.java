package com.waucher.waucher.Controllers.IssueController;

import com.waucher.waucher.Controllers.IssueController.Dto.CreateIssueRequest;
import com.waucher.waucher.Controllers.IssueController.Dto.GetIssueResponse;
import com.waucher.waucher.Controllers.IssueController.Dto.GetUserIssueResponse;
import com.waucher.waucher.Services.ExcelService.Interfaces.ExcelService;
import com.waucher.waucher.Services.IssueProcessing.Interfaces.IssueProcessingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;


@RestController
@RequestMapping("issueController")
public class IssueController {

    private final ExcelService excelService;

    private final IssueProcessingService issueService;

    @Autowired
    public IssueController(ExcelService excelService, IssueProcessingService issueService) {
        this.excelService = excelService;
        this.issueService = issueService;
    }

    @PostMapping("create")
    public ResponseEntity<String> CreateNewIssue(@RequestHeader UUID userId, @RequestBody CreateIssueRequest createIssueRequest){
        var issueId = issueService.createNewIssue(userId, createIssueRequest);
        return new ResponseEntity<>("Created", HttpStatus.ACCEPTED);
    }

    @GetMapping("getIssue")
    public ResponseEntity<GetIssueResponse> GetIssue(@RequestHeader UUID issueId){
        var response = issueService.getIssueById(issueId);
        return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
    }

    @GetMapping("getAllUserIssues")
    public ResponseEntity<List<GetUserIssueResponse>> GetAllUserIssues(@RequestHeader UUID userId){
        var response = issueService.getIssuesByEmployeeId(userId);
        return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
    }

    @GetMapping("getAllCompletedIssues")
    public ResponseEntity<List<GetIssueResponse>> getAllCompletedIssues(){
        var issues = issueService.getAllCompletedIssues();
        return new ResponseEntity<>(issues, HttpStatus.ACCEPTED);
    }

    @GetMapping(value = "getAllCompletedIssuesExcel",
            produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    public @ResponseBody ByteArrayResource getAllCompletedIssuesExcel(){
        excelService.createTable();
        Path path = Paths.get("result.xls");
        ByteArrayResource resource = null;
        try {
            resource = new ByteArrayResource(Files.readAllBytes(path));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return resource;
    }
}
