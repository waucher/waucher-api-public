package com.waucher.waucher.Services.Account;

import com.waucher.waucher.Controllers.AccountController.Dto.GetUserInfoResponse;
import com.waucher.waucher.Controllers.AccountController.Dto.RegisterUserRequest;
import com.waucher.waucher.Controllers.AdminController.Dto.CreateUserRequest;
import com.waucher.waucher.DAL.Enums.UserRole;
import com.waucher.waucher.DAL.Models.Employee;
import com.waucher.waucher.DAL.Repositories.EmployeeRepository;
import com.waucher.waucher.Services.Account.Interfaces.AccountService;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.net.PasswordAuthentication;
import java.util.UUID;

@Service
public class AccountServiceImpl implements AccountService {

    private EmployeeRepository employeeRepo;

    public AccountServiceImpl(EmployeeRepository employeeRepo) {
        this.employeeRepo = employeeRepo;
    }

    public UUID createNewUser(CreateUserRequest newUserRequest){
        var user = new Employee(
                newUserRequest.getFirstname(),
                newUserRequest.getLastname(),
                newUserRequest.getMiddlename(),
                newUserRequest.getEmail(),
                newUserRequest.getPassword(),
                newUserRequest.getRole()
        );

        var resultEntity = employeeRepo.save(user);
        return resultEntity.getId();
    }

    @Override
    public UUID registerNewUser(RegisterUserRequest newUserRequest) {
        var user = new Employee(
                newUserRequest.getFirstname(),
                newUserRequest.getLastname(),
                newUserRequest.getMiddlename(),
                newUserRequest.getEmail(),
                newUserRequest.getPassword(),
                UserRole.Customer
        );

        var resultEntity = employeeRepo.save(user);
        return resultEntity.getId();
    }

    public UUID getAuthUserUuid(String email, String password){
        var passwordString = password;
        var employee = employeeRepo.findByEmailAndPassword(email, passwordString);
        return employee.getId();
    }

    @Override
    public GetUserInfoResponse getUserInfo(UUID userId) {
        var employee = employeeRepo.findById(userId).get();
        return new GetUserInfoResponse(
                employee.getId(),
                employee.getFirstname(),
                employee.getLastname(),
                employee.getMiddlename(),
                employee.getEmail()
        );
    }
}
