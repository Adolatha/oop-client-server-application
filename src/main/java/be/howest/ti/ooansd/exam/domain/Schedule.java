package be.howest.ti.ooansd.exam.domain;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Schedule {

    private List<Shift> shifts;

    public Schedule(){
        this.shifts = new ArrayList<>();
    }

}
