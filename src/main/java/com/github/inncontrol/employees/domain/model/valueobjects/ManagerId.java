package com.github.inncontrol.employees.domain.model.valueobjects;

public record ManagerId(Long managerId) {
    public ManagerId {
        if (managerId < 0) {
            throw new IllegalArgumentException("Manager profileId cannot be negative");
        }
    }

    public ManagerId() {
        this(0L);
    }
}
