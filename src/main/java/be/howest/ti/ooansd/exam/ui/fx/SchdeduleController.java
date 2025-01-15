package be.howest.ti.ooansd.exam.ui.fx;

import java.net.URL;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.ResourceBundle;
import be.howest.ti.ooansd.exam.domain.Employee;

import be.howest.ti.ooansd.exam.data.mysql.EmployeeRepositoryImpl;
import be.howest.ti.ooansd.exam.data.mysql.ShiftRepositoryImpl;
import be.howest.ti.ooansd.exam.domain.Shift;
import be.howest.ti.ooansd.exam.service.EmployeeService;
import be.howest.ti.ooansd.exam.service.ShiftService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;

public class SchdeduleController {

    private final ShiftService shiftService = new ShiftService(new ShiftRepositoryImpl());
    private final EmployeeService employeeService = new EmployeeService(new EmployeeRepositoryImpl());

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private DatePicker Calendar;

    @FXML
    private ComboBox<String> EmployeeName;

    @FXML
    private ListView<String> ListOfShifts;

    @FXML
    private ComboBox<LocalTime> endTime;

    @FXML
    private ComboBox<LocalTime> startTime;

    @FXML
    private Label lblError;


    @FXML
    void onAddShift(ActionEvent event) {
        String employeeName = EmployeeName.getValue();
        LocalDate date = Calendar.getValue();
        LocalTime start = startTime.getValue();
        LocalTime end = endTime.getValue();

        if (employeeName == null || date == null || start == null || end == null) {
            lblError.setText("Please fill in all fields.");
            return;
        }

        try {
            shiftService.addShift(employeeName, date, start, end);
            lblError.setText("");
            loadShifts();
        } catch (IllegalArgumentException e) {
            lblError.setText(e.getMessage());
        }

    }

    @FXML
    void onDateSelection(ActionEvent event) {
        loadShifts();
    }

    @FXML
    void onEmployeeSelection(ActionEvent event) {
        loadShifts();

    }

    private void loadShifts() {
        String employeeName = EmployeeName.getValue();
        LocalDate date = Calendar.getValue();

        if (employeeName == null || date == null) {
            ListOfShifts.getItems().clear();
            return;
        }

        List<Shift> shifts = shiftService.getShiftsForEmployee(employeeName, date);

        ListOfShifts.getItems().clear();
        for (Shift shift : shifts) {
            ListOfShifts.getItems().add(
                    String.format("Start: %s, End: %s", shift.getStartTime(), shift.getEndTime())
            );
        }
    }

    @FXML
    void initialize() {
        assert Calendar != null : "fx:id=\"Calendar\" was not injected: check your FXML file 'Planner.fxml'.";
        assert EmployeeName != null : "fx:id=\"EmployeeName\" was not injected: check your FXML file 'Planner.fxml'.";
        assert ListOfShifts != null : "fx:id=\"ListOfShifts\" was not injected: check your FXML file 'Planner.fxml'.";
        assert endTime != null : "fx:id=\"endTime\" was not injected: check your FXML file 'Planner.fxml'.";
        assert startTime != null : "fx:id=\"startTime\" was not injected: check your FXML file 'Planner.fxml'.";

        List<String> employees = employeeService.getAllEmployees().stream()
                .map(Employee::getName)
                .toList();
        EmployeeName.getItems().addAll(employees);

        for (int hour = 6; hour <= 18; hour++) {
            LocalTime time = LocalTime.of(hour, 0);
            startTime.getItems().add(time);
            endTime.getItems().add(time);
        }

        lblError.setText("");

    }




}
