package be.howest.ti.ooansd.exam.data.repository;

import be.howest.ti.ooansd.exam.domain.Shift;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface ShiftRepository {
    Optional<Shift> findById(int id);
    List<Shift> findByEmployeeAndDate(String name, LocalDate date);
    void save(Shift shift);
    void update(Shift shift);
    void deleteById(int id);
}
