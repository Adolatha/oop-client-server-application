package be.howest.ti.ooansd.exam.domain;

import java.time.LocalDate;

public class CalendarDay {
    private LocalDate date;

    public CalendarDay(LocalDate date) {
        this.date = date;
    }

    public LocalDate getDate() {
        return date;
    }
}
