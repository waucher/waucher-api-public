package com.waucher.waucher.Services.JournalService;

import com.waucher.waucher.Controllers.Journal.Dto.GetUserJournalResponse;
import com.waucher.waucher.DAL.Repositories.JournalRepository;
import com.waucher.waucher.Services.JournalService.Interfaces.JournalService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class JournalServiceImpl implements JournalService {

    private final JournalRepository journalRepo;

    public JournalServiceImpl(JournalRepository journalRepo) {
        this.journalRepo = journalRepo;
    }


    @Override
    public List<GetUserJournalResponse> getUserJournal(UUID userId) {

        var userJournal = journalRepo.findAllByIssue_Employee_Id(userId).stream().map(
                journal -> new GetUserJournalResponse(journal.getId(),
                        journal.getCurrentIssueStatus(),
                        journal.getChangesDate(),
                        journal.getPreviousIssueStatus())
        ).toList();
        return userJournal;
    }
}
