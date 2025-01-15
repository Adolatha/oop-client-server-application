package be.howest.ti.ooansd.exam.domain;

import java.util.UUID;

public class Employee {
    private int id;
    private String name;
    private int workingHours;

    public Employee(int id, String name, int workingHours) {
        this.id = id;
        this.name = name;
        this.workingHours = workingHours;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }


}
