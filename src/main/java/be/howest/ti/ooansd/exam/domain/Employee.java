package be.howest.ti.ooansd.exam.domain;

import java.util.UUID;

public class Employee {
    private UUID id;
    private String name;
    private int workingHours;

    public Employee(String name, int workingHours) {
        this.id = UUID.randomUUID();
        this.name = name;
        this.workingHours = workingHours;
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getWorkingHours() {
        return workingHours;
    }

}
