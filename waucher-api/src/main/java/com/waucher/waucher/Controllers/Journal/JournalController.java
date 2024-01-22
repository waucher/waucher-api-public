package com.waucher.waucher.Controllers.Journal;

import com.waucher.waucher.Controllers.Journal.Dto.GetUserJournalResponse;
import com.waucher.waucher.Services.IssueProcessing.IssueProcessingServiceImpl;
import com.waucher.waucher.Services.JournalService.Interfaces.JournalService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("journalController")
public class JournalController {


    private final JournalService journalService;

    public JournalController(JournalService journalService) {
        this.journalService = journalService;
    }

    @GetMapping("getAllUserIssues")
    public ResponseEntity<List<GetUserJournalResponse>> getAllUserIssues(@RequestHeader UUID userId){
        var response = journalService.getUserJournal(userId);
        return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
    }
}
