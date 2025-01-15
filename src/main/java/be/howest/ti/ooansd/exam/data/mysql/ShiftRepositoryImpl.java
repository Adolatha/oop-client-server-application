package be.howest.ti.ooansd.exam.data.mysql;

import be.howest.ti.ooansd.exam.data.repository.ShiftRepository;
import be.howest.ti.ooansd.exam.domain.Shift;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ShiftRepositoryImpl implements ShiftRepository {

    public ShiftRepositoryImpl() {
    }
    @Override
    public Optional<Shift> findById(int id) {
        String query = "SELECT * FROM shifts WHERE id = ?";
        try (Connection connection = MysqlConnection.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return Optional.of(new Shift(
                        rs.getInt("id"),
                        rs.getDate("date").toLocalDate(),
                        rs.getString("employee_name"),
                        rs.getTime("start_time").toLocalTime(),
                        rs.getTime("end_time").toLocalTime()
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }



    @Override
    public List<Shift> findByEmployeeAndDate(String employeeName, LocalDate date) {
        System.out.println("Fetching shifts for " + employeeName + " on " + date); // Debugging

        List<Shift> shifts = new ArrayList<>();
        String query = "SELECT * FROM shifts WHERE employee_name = ? AND date = ?";
        try (Connection connection = MysqlConnection.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, employeeName);
            stmt.setDate(2, Date.valueOf(date));
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                shifts.add(new Shift(
                        rs.getInt("id"),
                        rs.getDate("date").toLocalDate(),
                        rs.getString("employee_name"),
                        rs.getTime("start_time").toLocalTime(),
                        rs.getTime("end_time").toLocalTime()
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return shifts;
    }
    public void save(Shift shift) {
        String query = "INSERT INTO shifts (date, employee_name, start_time, end_time) VALUES (?, ?, ?, ?)";
        try (Connection connection = MysqlConnection.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setDate(1, Date.valueOf(shift.getDate()));
            stmt.setString(2, shift.getEmployeeName());
            stmt.setTime(3, Time.valueOf(shift.getStartTime()));
            stmt.setTime(4, Time.valueOf(shift.getEndTime()));
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Shift shift) {
        String query = "UPDATE shifts SET start_time = ?, end_time = ? WHERE id = ?";
        try (Connection connection = MysqlConnection.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setTime(1, Time.valueOf(shift.getStartTime()));
            stmt.setTime(2, Time.valueOf(shift.getEndTime()));
            stmt.setInt(3, shift.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteById(int id) {
        String query = "DELETE FROM shifts WHERE id = ?";
        try (Connection connection = MysqlConnection.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
