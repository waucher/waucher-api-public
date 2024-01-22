package com.waucher.waucher.DAL.Repositories;

import com.waucher.waucher.DAL.Models.Employee;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;
import java.util.List;


@Repository
public interface EmployeeRepository extends CrudRepository<Employee, UUID> {
    Employee findByEmailAndPassword(String email, String password);
}
