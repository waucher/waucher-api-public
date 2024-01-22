package com.waucher.waucher.Controllers.AccountController;

import com.waucher.waucher.Controllers.AccountController.Dto.GetUserInfoResponse;
import com.waucher.waucher.Controllers.AccountController.Dto.RegisterUserRequest;
import com.waucher.waucher.Services.Account.Interfaces.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController()
@RequestMapping("account")
public class AccountController {

    private final AccountService accountService;

    @Autowired
    public AccountController(AccountService accountService){
        this.accountService = accountService;
    }

    @PostMapping("register")
    public ResponseEntity<UUID> registerNewUser(@RequestBody RegisterUserRequest request){
        var id = accountService.registerNewUser(request);
        return new ResponseEntity<>(id, HttpStatus.ACCEPTED);
    }

    @GetMapping("authenticate")
    public ResponseEntity<UUID> authenticate(String email, String password){
        var id = accountService.getAuthUserUuid(email, password);
        return new ResponseEntity<>(id, HttpStatus.ACCEPTED);
    }

    @GetMapping("getEmployeeInfo")
    public ResponseEntity<GetUserInfoResponse> getEmployeeInfo(@Header UUID userId){
        var info = accountService.getUserInfo(userId);
        return new ResponseEntity<>(info, HttpStatus.ACCEPTED);
    }
}
