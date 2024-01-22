package com.waucher.waucher.Controllers.AccountController.Dto;

import com.waucher.waucher.DAL.Enums.UserRole;

public class RegisterUserRequest {
    private String firstname;

    private String lastname;

    private String middlename = null;

    private String email;

    private String password;

    public RegisterUserRequest(String firstname, String lastname, String middlename, String email, String password, UserRole role) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.middlename = middlename;
        this.email = email;
        this.password = password;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public String getMiddlename() {
        return middlename;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
}
