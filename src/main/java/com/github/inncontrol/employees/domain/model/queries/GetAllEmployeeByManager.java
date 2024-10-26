package com.github.inncontrol.employees.domain.model.queries;

import com.github.inncontrol.employees.domain.model.aggregates.Employee;

public record GetAllEmployeeByManager(Employee manager) {
}
