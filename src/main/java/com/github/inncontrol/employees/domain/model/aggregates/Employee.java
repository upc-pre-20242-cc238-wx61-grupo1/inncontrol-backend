package com.github.inncontrol.employees.domain.model.aggregates;

import com.github.inncontrol.employees.domain.model.valueobjects.*;
import com.github.inncontrol.shared.domain.aggregates.AuditableAbstractAggregateRoot;
import jakarta.persistence.*;
import lombok.Getter;

import java.util.Date;

@Entity
public class Employee extends AuditableAbstractAggregateRoot<Employee> {

    @Embedded
    private ContractInformation contract;

    @Getter
    @Enumerated(EnumType.STRING)
    private Role role;

    @Embedded
    private SalaryEmployee salary;

    @Embedded
    private ProfileId profileId;

    @Getter
    @ManyToOne
    @JoinColumn(name = "manager_id")
    private Employee manager;

    public Employee() {
    }

    public Employee(ContractInformation contract, Role role, SalaryEmployee salary) {
        this.contract = contract;
        this.role = role != null ? role : Role.EMPLOYEE;
        this.salary = salary;
    }

    public Employee(ProfileId profileId, Double salary, ContractInformation contract) {
        this();
        this.salary = new SalaryEmployee(salary);
        this.profileId = profileId;
        this.role = role != null ? role : Role.EMPLOYEE;
        this.contract = contract;
    }

    public Employee(ProfileId profileId, Double salary, ContractInformation contract, Role role) {
        this();
        this.salary = new SalaryEmployee(salary);
        this.profileId = profileId;
        this.role = role;
        this.contract = contract;
    }

    public Employee(ProfileId profileId, Double salary, ContractInformation contract, Role role, Employee manager) {
        this(profileId, salary, contract, role);
        this.manager = manager;
    }

    public Employee updateInformation(Double salary, ContractInformation contract, Role role) {
        this.salary = new SalaryEmployee(salary);
        this.contract = contract;
        this.role = role;
        return this;
    }


    public Double getContractRemuneration() {
        return salary.salary() * contract.getMonthsWorked();
    }

    public Long getProfileId() {
        return this.profileId.profileId();
    }

    public void ascendToManager() {
        this.role = Role.MANAGER;
    }

    public void downgradeToEmployee() {
        this.role = Role.EMPLOYEE;
    }

    public Date getInitiationContract() {
        return this.contract.initiationDate();
    }

    public Date getTerminateContract() {
        return this.contract.terminationDate();
    }

    public Double getSalary() {
        return this.salary.salary();
    }

    public Long getManagerId() {
        return this.manager != null ? this.manager.getId() : null;
    }


    public Employee updateSalary(Double salary) {
        this.salary = this.salary.updateSalary(salary);
        return this;
    }
}
