package com.waucher.waucher.DAL.Repositories;

import com.waucher.waucher.DAL.Models.ValidOrganization;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface ValidOrganizationRepository extends CrudRepository<ValidOrganization, UUID> {
    Optional<ValidOrganization> findByInn(String inn);
}
