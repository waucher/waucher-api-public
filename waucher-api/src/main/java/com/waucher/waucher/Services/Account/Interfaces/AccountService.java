package com.waucher.waucher.Services.Account.Interfaces;

import com.waucher.waucher.Controllers.AccountController.Dto.GetUserInfoResponse;
import com.waucher.waucher.Controllers.AccountController.Dto.RegisterUserRequest;
import com.waucher.waucher.Controllers.AdminController.Dto.CreateUserRequest;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public interface AccountService {

    public UUID createNewUser(CreateUserRequest newUserRequest);

    public UUID registerNewUser(RegisterUserRequest newUserRequest);

    public UUID getAuthUserUuid(String email, String password);

    public GetUserInfoResponse getUserInfo(UUID userId);
}
