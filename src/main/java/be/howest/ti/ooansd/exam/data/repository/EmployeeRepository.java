package be.howest.ti.ooansd.exam.data.repository;

import be.howest.ti.ooansd.exam.domain.Employee;

import java.util.List;
import java.util.Optional;

public interface EmployeeRepository {
    Optional<Employee> findById(int id);
    Optional<Employee> findByName(String name);
    List<Employee> findAll();
    void save(Employee employee);
    void update(Employee employee);
    void deleteById(int id);
}
