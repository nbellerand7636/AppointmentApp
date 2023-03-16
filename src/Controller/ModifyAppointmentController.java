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
 * This class implements the logic for the Modify Appointment Screen
 * */
public class ModifyAppointmentController implements Initializable {

    public static Appointment theAppointment;
    public TextField appointmentID;
    public TextField appointmentDescription;
    public TextField appointmentTitle;
    public TextField appointmentType;
    public TextField appointmentCustomerID;
    public TextField appointmentUserID;
    public ComboBox<Contact> contactComboBox;
    public TextField appointmentLocation;
    public DatePicker appointmentStartDate;
    public DatePicker appointmentEndDate;
    public ComboBox<LocalTime>appointmentStartTime;
    public ComboBox<LocalTime> appointmentEndTime;
    ObservableList<LocalTime> appointmentTimes = FXCollections.observableArrayList();
    /**
     * @param newAppointment
     * This function gets the appointment from the Home screen so the information can be displayed
     * */
    public static void sendAppointmentData(Appointment newAppointment){
        theAppointment = newAppointment;
    }

    /**
     * This function initializes the Modify Appointment Screen
     * */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        appointmentID.setText(String.valueOf(theAppointment.getAppointmentID()));
        appointmentTitle.setText(theAppointment.getTitle());
        appointmentDescription.setText(theAppointment.getDescription());
        appointmentLocation.setText(theAppointment.getLocation());
        contactComboBox.setItems(DBContact.getAllContacts());
        for(Contact c: contactComboBox.getItems()){
            if(c.getContactID()== theAppointment.getContactID()){
                contactComboBox.setValue(c);
            }
        }
        LocalTime start = LocalTime.of(7,30,0);
        LocalTime end = LocalTime.of(22,0,0);
        while(start.isBefore(end.plusSeconds(1))){
            start = start.plusMinutes(30);
            appointmentTimes.add(start);
        }
        appointmentStartTime.setItems(appointmentTimes);
        appointmentEndTime.setItems(appointmentTimes);
        appointmentType.setText(theAppointment.getType());
        appointmentStartDate.setValue(theAppointment.getStart().toLocalDate());
        appointmentStartTime.setValue(theAppointment.getStart().toLocalTime());
        appointmentEndDate.setValue(theAppointment.getEnd().toLocalDate());
        appointmentEndTime.setValue(theAppointment.getEnd().toLocalTime());
        appointmentCustomerID.setText(String.valueOf(theAppointment.getCustomerID()) );
        appointmentUserID.setText(String.valueOf(theAppointment.getUserID()));
    }
    /**
     * This function updates the Appointment and returns to the Home Page
     * */
    public void onSubmitClicked(ActionEvent actionEvent) throws IOException {
        String title = appointmentTitle.getText();
        String description = appointmentDescription.getText();
        String location = appointmentLocation.getText();
        int contact = contactComboBox.getValue().getContactID();
        String type = appointmentType.getText();
        LocalDate startDate =  appointmentStartDate.getValue();
        LocalDate endDate = appointmentEndDate.getValue();
        LocalTime startTime = appointmentStartTime.getValue();
        LocalTime endTime = appointmentEndTime.getValue();
        LocalDateTime start =LocalDateTime.of(startDate,startTime);
        LocalDateTime end = LocalDateTime.of(endDate,endTime);
        int customerID = Integer.parseInt(appointmentCustomerID.getText());
        int appointment = Integer.parseInt(appointmentID.getText());

        ZoneId estZone = ZoneId.of("America/New_York");
        ZoneId localZone = ZoneId.systemDefault();
        LocalDateTime estStart = LocalDateTime.of(startDate,LocalTime.of(8,0));
        ZonedDateTime estZDTStart = estStart.atZone(estZone);
        ZonedDateTime localZDTStart = estZDTStart.withZoneSameInstant(localZone);
        LocalDateTime startBH = localZDTStart.toLocalDateTime();
        if(startBH.isAfter(start)){
            System.out.println("Business hours are 8 AM - 10 PM. Try again !");
        }

        LocalDateTime estEnd = LocalDateTime.of(endDate,LocalTime.of(22,0));
        ZonedDateTime estZDTEnd = estEnd.atZone(estZone);
        ZonedDateTime localZDTEnd = estZDTEnd.withZoneSameInstant(localZone);
        LocalDateTime endBH = localZDTEnd.toLocalDateTime();
        if(endBH.isBefore(end)){
            System.out.println("Business hours are 8 AM - 10 PM. Try again !");
            return;
        }
        for(Appointment a: DBAppointment.getAllAppointments()){
            if((a.getCustomerID()==customerID) && (a.getAppointmentID() != appointment)){
                if(((start.isAfter(a.getStart())) || (start.isEqual(a.getStart()))) && (start.isBefore(a.getEnd()))){
                    System.out.println("Customer already has an appointment for this time");
                    return;
                }   else if(end.isAfter(a.getStart()) && (end.isBefore(a.getEnd()) || (end.isEqual(a.getEnd())))){
                    System.out.println("Customer already has an appointment for this time");
                    return;
                }   else if(((start.isBefore(a.getStart())) || (start.isEqual(a.getStart()))) && ((end.isAfter(a.getEnd())) || (end.isEqual(a.getEnd())))){
                    System.out.println("Customer already has an appointment for this time");
                    return;
                }
            }
        }

        DBAppointment.updateAppointment(title,description,location,contact,type,start,end,customerID,appointment);
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/view/HomePage.fxml")));
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root, 900, 700);
        stage.setTitle("Home Page");
        stage.setScene(scene);
        stage.show();
    }
    /**
     * This function cancels and returns to the Home Page
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
