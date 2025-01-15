package be.howest.ti.ooansd.exam.data.mysql;

import be.howest.ti.ooansd.exam.data.repository.EmployeeRepository;
import be.howest.ti.ooansd.exam.domain.Employee;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class EmployeeRepositoryImpl implements EmployeeRepository {

    public EmployeeRepositoryImpl() {
    }
    @Override
    public Optional<Employee> findById(int id) {
        String query = "SELECT * FROM employees WHERE id = ?";
        try (Connection connection = MysqlConnection.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return Optional.of(new Employee(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getInt("max_hours")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public Optional<Employee> findByName(String name) {
        return Optional.empty();
    }

    @Override
    public List<Employee> findAll() {
        List<Employee> employees = new ArrayList<>();
        String query = "SELECT * FROM employees";
        try (Connection connection = MysqlConnection.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                employees.add(new Employee(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getInt("max_hours")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return employees;
    }
    @Override
    public void save(Employee employee) {
        String query = "INSERT INTO employees (name, max_hours) VALUES (?, ?)";
        try (Connection connection = MysqlConnection.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, employee.getName());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Employee employee) {
        String query = "UPDATE employees SET name = ?, max_hours = ? WHERE id = ?";
        try (Connection connection = MysqlConnection.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, employee.getName());
            stmt.setInt(3, employee.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteById(int id) {
        String query = "DELETE FROM employees WHERE id = ?";
        try (Connection connection = MysqlConnection.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}
