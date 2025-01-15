package be.howest.ti.ooansd.exam.data.repository;

import be.howest.ti.ooansd.exam.data.mysql.EmployeeRepositoryImpl;
import be.howest.ti.ooansd.exam.data.mysql.ShiftRepositoryImpl;

public class Repositories {
    private static final EmployeeRepository EMPLOYEE_REPOSITORY = new EmployeeRepositoryImpl();
    private static final ShiftRepository SHIFT_REPOSITORY = new ShiftRepositoryImpl();

    public static EmployeeRepository getEmployeeRepository() {
        return EMPLOYEE_REPOSITORY;
    }

    public static ShiftRepository getShiftRepository() {
        return SHIFT_REPOSITORY;
    }
}
