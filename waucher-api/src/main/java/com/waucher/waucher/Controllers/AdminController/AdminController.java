package com.waucher.waucher.Controllers.AdminController;

import com.waucher.waucher.Controllers.AdminController.Dto.CreateUserRequest;
import com.waucher.waucher.Services.Account.Interfaces.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController()
@RequestMapping("admin")
public class AdminController {

    private final AccountService accountService;

    @Autowired
    public AdminController(AccountService accountService) {
        this.accountService = accountService;
    }

    @PostMapping("user/create")
    public ResponseEntity<String> CreateNewUser(@RequestBody CreateUserRequest createUserRequest){
        var userId = accountService.createNewUser(createUserRequest);
        return new ResponseEntity<>("SAVE", HttpStatus.ACCEPTED);
    }
}
