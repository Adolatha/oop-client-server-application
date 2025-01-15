package be.howest.ti.ooansd.exam.service;

import be.howest.ti.ooansd.exam.data.repository.ShiftRepository;
import be.howest.ti.ooansd.exam.domain.Schedule;
import be.howest.ti.ooansd.exam.domain.Shift;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

public class ShiftService {
    private final ShiftRepository shiftRepository;
    private final Schedule schedule;

    public ShiftService(ShiftRepository shiftRepository) {
        this.shiftRepository = shiftRepository;
        this.schedule = new Schedule();
    }

    public void addShift(String employeeName, LocalDate date, LocalTime startTime, LocalTime endTime) {
        if (!isValidShiftTime(startTime, endTime)) {
            throw new IllegalArgumentException("Shift times must be between 06:00 and 18:00 and not overlapping.");
        }

        // Fetch existing shifts for validation
        List<Shift> existingShifts = shiftRepository.findByEmployeeAndDate(employeeName, date);
        if (isOverlapping(existingShifts, startTime, endTime)) {
            throw new IllegalArgumentException("Shift times overlap with an existing shift.");
        }

        // Save the shift
        Shift shift = new Shift(0, date, employeeName, startTime, endTime);
        shiftRepository.save(shift);
    }


    public List<Shift> getShiftsForEmployee(String employeeName, LocalDate date) {
        return shiftRepository.findByEmployeeAndDate(employeeName, date);
    }


    private boolean isValidShiftTime(LocalTime startTime, LocalTime endTime) {
        LocalTime earliest = LocalTime.of(6, 0);
        LocalTime latest = LocalTime.of(18, 0);
        return !startTime.isBefore(earliest) && !endTime.isAfter(latest) && startTime.isBefore(endTime);
    }

    private boolean isOverlapping(List<Shift> existingShifts, LocalTime startTime, LocalTime endTime) {
        for (Shift existingShift : existingShifts) {
            if (startTime.isBefore(existingShift.getEndTime()) && endTime.isAfter(existingShift.getStartTime())) {
                return true;
            }
        }
        return false;
    }
}
