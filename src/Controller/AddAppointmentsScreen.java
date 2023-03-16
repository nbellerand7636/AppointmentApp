package Controller;

import Model.Appointment;
import Model.Contact;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import utils.DBAppointment;
import utils.DBContact;

import java.io.IOException;
import java.net.URL;
import java.time.*;
import java.util.Objects;
import java.util.ResourceBundle;

/**
 * This class implements the logic for the Add Appointment Screen
 * */
public class AddAppointmentsScreen implements Initializable {
    public TextField appointmentID;
    public TextField appointmentDescription;
    public TextField appointmentTitle;
    public TextField start;
    public TextField Type;
    public TextField end;
    public TextField customerID;
    public TextField userID;
    public ComboBox <Contact> contactComboBox;
    public TextField location;
    public TextField appointmentLocation;
    public TextField appointmentType;
    public TextField appointmentCustomerID;
    public TextField appointmentUserID;
    public DatePicker appointmentStartDate;
    public DatePicker appointmentEndDate;
    public ComboBox appointmentStartTime;
    public ComboBox appointmentEndTime;


    ObservableList <LocalTime> appointmentTimes = FXCollections.observableArrayList();
    /**
     *This function loads the Add Appointments screen and any code that runs on initialization.
     * */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    contactComboBox.setItems(DBContact.getAllContacts());


        LocalTime start = LocalTime.of(6,0,0);
        LocalTime end = LocalTime.of(23,0,0);
        while(start.isBefore(end.plusSeconds(1))){
            start = start.plusMinutes(30);
            appointmentTimes.add(start);
        }
        appointmentStartTime.setItems(appointmentTimes);
        appointmentEndTime.setItems(appointmentTimes);
        appointmentUserID.setText(String.valueOf(LoginScreenController.theUser.getUserid()));

    }
    /**
     * This function adds a new appointment to the DB and returns to the home page.
     * */
    public void onSubmitClicked(ActionEvent actionEvent) throws IOException {
        String description = appointmentDescription.getText();
        String title = appointmentTitle.getText();
        String location = appointmentLocation.getText();
        String type = appointmentType.getText();
        LocalDate startDate =  appointmentStartDate.getValue();
        LocalDate endDate = appointmentEndDate.getValue();
        LocalTime startTime = (LocalTime) appointmentStartTime.getValue();
        LocalTime endTime = (LocalTime) appointmentEndTime.getValue();
        LocalDateTime start =LocalDateTime.of(startDate,startTime);
        LocalDateTime end = LocalDateTime.of(endDate,endTime);
        int contactID = contactComboBox.getValue().getContactID();
        int customerID = Integer.parseInt(appointmentCustomerID.getText());
        int userID = Integer.parseInt(appointmentUserID.getText());
        ZoneId estZone = ZoneId.of("America/New_York");
        ZoneId localZone = ZoneId.systemDefault();
        LocalDateTime estStart = LocalDateTime.of(startDate,LocalTime.of(8,0));
        ZonedDateTime estZDTStart = estStart.atZone(estZone);
        ZonedDateTime localZDTStart = estZDTStart.withZoneSameInstant(localZone);
        LocalDateTime startBH = localZDTStart.toLocalDateTime();
        if(startBH.isAfter(start)){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning Dialog");
            alert.setHeaderText("Scheduling Conflict");
            alert.setContentText("Business hours are 8 AM - 10 PM. Try again !");
            alert.showAndWait();
            return;
        }

        LocalDateTime estEnd = LocalDateTime.of(endDate,LocalTime.of(22,0));
        ZonedDateTime estZDTEnd = estEnd.atZone(estZone);
        ZonedDateTime localZDTEnd = estZDTEnd.withZoneSameInstant(localZone);
        LocalDateTime endBH = localZDTEnd.toLocalDateTime();
        if(endBH.isBefore(end)){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning Dialog");
            alert.setHeaderText("Scheduling Conflict");
            alert.setContentText("Business hours are 8 AM - 10 PM. Try again !");
            alert.showAndWait();
            return;
        }

        for(Appointment a: DBAppointment.getAllAppointments()){
            if(a.getCustomerID()==customerID){
                if((start.isAfter(a.getStart()) || (start.isEqual(a.getStart()))) && (start.isBefore(a.getEnd()))){
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("Warning Dialog");
                    alert.setHeaderText("Scheduling Conflict");
                    alert.setContentText("Customer already has an appointment for this time");
                    alert.showAndWait();
                        return;
                }   else if(end.isAfter(a.getStart()) && (end.isBefore(a.getEnd()) || (end.isEqual(a.getEnd())))){
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("Warning Dialog");
                    alert.setHeaderText("Scheduling Conflict");
                    alert.setContentText("Customer already has an appointment for this time");
                    alert.showAndWait();
                    return;
                }   else if(((start.isBefore(a.getStart())) || (start.isEqual(a.getStart()))) && ((end.isAfter(a.getEnd())) || (end.isEqual(a.getEnd())))){
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("Warning Dialog");
                    alert.setHeaderText("Scheduling Conflict");
                    alert.setContentText("Customer already has an appointment for this time");
                    alert.showAndWait();
                    return;
                }
            }
        }

        DBAppointment.createAppointment(title,description,location,type,start,end,customerID,userID,contactID);
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/view/HomePage.fxml")));
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root, 900, 700);
        stage.setTitle("Home Page");
        stage.setScene(scene);
        stage.show();
    }
    /**
     * This function cancels and returns to the home page.
     * */
    public void onCancelClicked(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/view/HomePage.fxml")));
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root, 900, 700);
        stage.setTitle("Home Page");
        stage.setScene(scene);
        stage.show();
    }
}
