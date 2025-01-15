package be.howest.ti.ooansd.exam.service;

import be.howest.ti.ooansd.exam.data.repository.EmployeeRepository;
import be.howest.ti.ooansd.exam.domain.Employee;

import java.util.List;
import java.util.Optional;

public class EmployeeService {
    private final EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public Employee getEmployeeByName(String name) {
        return employeeRepository.findByName(name)
                .orElseThrow(() -> new IllegalArgumentException("Employee not found."));
    }

    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    public void deleteEmployeeByName(String name) {
        Employee employee = employeeRepository.findByName(name)
                .orElseThrow(() -> new IllegalArgumentException("Employee not found."));
        employeeRepository.deleteById(employee.getId());
    }
}
