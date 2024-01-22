package com.waucher.waucher.Services.OrganizationService.Interfaces;

import com.waucher.waucher.Controllers.OrganizationController.Dto.AddNewOrganizationRequest;
import com.waucher.waucher.Controllers.OrganizationController.Dto.GetValidOrganizationResponse;
import com.waucher.waucher.Controllers.OrganizationController.Dto.UpdateValidOrganizationRequest;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public interface OrganizationService {
    UUID addNewValidOrganization(AddNewOrganizationRequest request);

    boolean deleteValidOrganization(UUID organizationId);

    GetValidOrganizationResponse getValidOrganization(UUID organizationId);

    GetValidOrganizationResponse updateValidOrganization(UUID organizationId, UpdateValidOrganizationRequest request);
}
