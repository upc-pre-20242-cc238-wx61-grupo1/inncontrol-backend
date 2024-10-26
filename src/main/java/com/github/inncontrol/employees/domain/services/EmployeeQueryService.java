package com.github.inncontrol.employees.domain.services;

import com.github.inncontrol.employees.domain.model.aggregates.Employee;
import com.github.inncontrol.employees.domain.model.queries.*;

import java.util.List;
import java.util.Optional;

public interface  EmployeeQueryService {

    List<Employee>handle(GetAllEmployeeQuery query);
    Optional<Employee> handle(GetEmployeeByIdQuery query);
    Optional<Employee> handle(GetEmployeeByRoleStatus query);
    Optional<Employee> handle(GetEmployeeByProfileIdQuery query);
    List<Employee>handle(GetAllEmployeeByManager query);
}
