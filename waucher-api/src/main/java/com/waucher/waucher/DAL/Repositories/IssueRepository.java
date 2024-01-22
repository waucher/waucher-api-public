package com.waucher.waucher.DAL.Repositories;

import com.waucher.waucher.DAL.Enums.IssueStatus;
import com.waucher.waucher.DAL.Models.Issue;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface IssueRepository extends CrudRepository<Issue, UUID> {
    List<Issue> findAllByEmployeeId(UUID employee);

    Optional<Issue> findByTicketId(String ticketId);

    List<Issue> findAllByStatus(IssueStatus status);
}
