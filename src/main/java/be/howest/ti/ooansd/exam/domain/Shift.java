package be.howest.ti.ooansd.exam.domain;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;

public class Shift {
    private UUID id;
    private UUID employeeId;
    private LocalDate date;
    private LocalTime startTime;
    private LocalTime endTime;

    public Shift(UUID employeeId, LocalDate date, LocalTime startTime, LocalTime endTime) {
        if (!isValidTime(startTime, endTime)) {
            throw new IllegalArgumentException("Shift times must be between 06:00 and 18:00");
        }
        this.id = UUID.randomUUID();
        this.employeeId = employeeId;
        this.date = date;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    private boolean isValidTime(LocalTime startTime, LocalTime endTime) {
        LocalTime earliest = LocalTime.of(6, 0);
        LocalTime latest = LocalTime.of(18, 0);
        return !startTime.isBefore(earliest) && !endTime.isAfter(latest) && startTime.isBefore(endTime);
    }
}
