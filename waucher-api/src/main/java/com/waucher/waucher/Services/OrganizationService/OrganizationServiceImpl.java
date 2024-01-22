package com.waucher.waucher.Services.OrganizationService;

import com.waucher.waucher.Controllers.OrganizationController.Dto.AddNewOrganizationRequest;
import com.waucher.waucher.Controllers.OrganizationController.Dto.GetValidOrganizationResponse;
import com.waucher.waucher.Controllers.OrganizationController.Dto.UpdateValidOrganizationRequest;
import com.waucher.waucher.DAL.Models.ValidOrganization;
import com.waucher.waucher.DAL.Repositories.IssueRepository;
import com.waucher.waucher.DAL.Repositories.ValidOrganizationRepository;
import com.waucher.waucher.Services.OrganizationService.Interfaces.OrganizationService;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class OrganizationServiceImpl implements OrganizationService {
    private final ValidOrganizationRepository validOrganizationRepo;

    public OrganizationServiceImpl(ValidOrganizationRepository validOrganizationRepo) {
        this.validOrganizationRepo = validOrganizationRepo;
    }

    @Override
    public UUID addNewValidOrganization(AddNewOrganizationRequest request) {
        var organization = new ValidOrganization(request.getInn(), request.getName());
        organization = validOrganizationRepo.save(organization);
        return organization.getId();
    }

    @Override
    public boolean deleteValidOrganization(UUID organizationId) {
        validOrganizationRepo.deleteById(organizationId);
        return true;
    }

    @Override
    public GetValidOrganizationResponse getValidOrganization(UUID organizationId) {
        var validOrganization = validOrganizationRepo.findById(organizationId).get();
        return new GetValidOrganizationResponse(validOrganization.getId(), validOrganization.getInn(), validOrganization.getName());
    }

    @Override
    public GetValidOrganizationResponse updateValidOrganization(UUID organizationId, UpdateValidOrganizationRequest request) {
        var validOrganization = validOrganizationRepo.findById(organizationId).get();
        validOrganization.setInn(request.getInn());
        validOrganization.setName(request.getName());
        validOrganization = validOrganizationRepo.save(validOrganization);
        return new GetValidOrganizationResponse(validOrganization.getId(), validOrganization.getInn(), validOrganization.getName());
    }
}
