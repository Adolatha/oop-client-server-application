package be.howest.ti.ooansd.exam.domain;

import java.time.LocalDate;
import java.time.LocalTime;

public class Shift {
    private int id;
    private LocalDate date;
    private String employeeName;
    private LocalTime startTime;
    private LocalTime endTime;

    public Shift(int id, LocalDate date, String employeeName, LocalTime startTime, LocalTime endTime) {
        this.id = id;
        this.date = date;
        this.employeeName = employeeName;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public String getEmployeeName() {
        return employeeName;
    }
    public LocalDate getDate() {
        return date;
    }
    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public void setEndTime(LocalTime endTime) {
        this.endTime = endTime;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }
}
