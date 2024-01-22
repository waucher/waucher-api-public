package com.waucher.waucher.Services.JournalService.Interfaces;

import com.waucher.waucher.Controllers.Journal.Dto.GetUserJournalResponse;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public interface JournalService {
    List<GetUserJournalResponse> getUserJournal(UUID userId);
}
