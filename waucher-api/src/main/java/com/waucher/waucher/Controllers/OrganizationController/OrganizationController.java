package com.waucher.waucher.Controllers.OrganizationController;

import com.waucher.waucher.Controllers.AccountController.Dto.RegisterUserRequest;
import com.waucher.waucher.Controllers.OrganizationController.Dto.AddNewOrganizationRequest;
import com.waucher.waucher.Controllers.OrganizationController.Dto.GetValidOrganizationResponse;
import com.waucher.waucher.Controllers.OrganizationController.Dto.UpdateValidOrganizationRequest;
import com.waucher.waucher.Services.OrganizationService.Interfaces.OrganizationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController()
@RequestMapping("organization")
public class OrganizationController {

    private final OrganizationService organizationService;

    public OrganizationController(OrganizationService organizationService) {
        this.organizationService = organizationService;
    }

    @PostMapping("addNewOrganization")
    public ResponseEntity<UUID> addNewOrganization(@RequestBody AddNewOrganizationRequest request){
        var id = organizationService.addNewValidOrganization(request);
        return new ResponseEntity<>(id, HttpStatus.ACCEPTED);
    }

    public ResponseEntity<Boolean> deleteOrganization(@Header UUID organizationUUID){
        var result = organizationService.deleteValidOrganization(organizationUUID);
        return new ResponseEntity<>(result, HttpStatus.ACCEPTED);
    }

    public ResponseEntity<GetValidOrganizationResponse> getValidOrganization(@Header UUID organizationUUID){
        var result = organizationService.getValidOrganization(organizationUUID);
        return new ResponseEntity<>(result, HttpStatus.ACCEPTED);
    }

    public ResponseEntity<GetValidOrganizationResponse> getValidOrganization(@Header UUID organizationUUID,
                                                                             @RequestBody UpdateValidOrganizationRequest updateValidOrganizationRequest){
        var result = organizationService.updateValidOrganization(organizationUUID, updateValidOrganizationRequest);
        return new ResponseEntity<>(result, HttpStatus.ACCEPTED);
    }
}
