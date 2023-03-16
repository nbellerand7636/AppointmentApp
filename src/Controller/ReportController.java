package Controller;

import Model.Appointment;
import Model.Contact;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import utils.DBAppointment;
import utils.DBContact;

import java.net.URL;
import java.time.LocalDateTime;
import java.util.ResourceBundle;

/**
 * This class implements the logic for the Report Screen
 * */
public class ReportController implements Initializable {

    public TableView appointmentTypeTable;
    public TableView appointmentMonthTable;
    public ComboBox appointmentTypeComboBox;
    public ComboBox appointmentMonthComboBox;
    public TableView appointmentContactTable;
    public ComboBox appointmentContactComboBox;
    public TableView pastAppointmentTable;
    public TextField appointmentType;
    public TableColumn appointment_ID;
    public TableColumn title;
    public TableColumn description;
    public TableColumn location;
    public TableColumn contact;
    public TableColumn type;
    public TableColumn start;
    public TableColumn end;
    public TableColumn customer_Id;
    public TableColumn user_Id;
    public Label countLabel;
    public TableColumn pastAppointmentID;
    public TableColumn pastAppointmentTitle;
    public TableColumn pastAppointmentDescription;
    public TableColumn pastAppointmentLocation;
    public TableColumn pastAppointmentContact;
    public TableColumn pastAppointmentType;
    public TableColumn pastAppointmentStart;
    public TableColumn pastAppointmentEnd;
    public TableColumn pastAppointmentCustomerID;
    public TableColumn pastAppointmentUserID;

    /**
     * This function initializes the Report screen
     * */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        appointmentTypeComboBox.setItems(DBAppointment.getAppointmentTypes());
        ObservableList<String>months = FXCollections.observableArrayList();
        months.add("January");
        months.add("February");
        months.add("March");
        months.add("April");
        months.add("May");
        months.add("June");
        months.add("July");
        months.add("August");
        months.add("September");
        months.add("October");
        months.add("November");
        months.add("December");
        appointmentMonthComboBox.setItems(months);
        appointmentContactComboBox.setItems(DBContact.getAllContacts());
        ObservableList<Appointment>pastAppointments=FXCollections.observableArrayList();
        for(Appointment a: DBAppointment.getAllAppointments()){
            if(a.getEnd().isBefore(LocalDateTime.now())){
                pastAppointments.add(a);
            }
        }
        pastAppointmentTable.setItems(pastAppointments);
        pastAppointmentID.setCellValueFactory(new PropertyValueFactory<>("appointmentID"));
        pastAppointmentTitle.setCellValueFactory(new PropertyValueFactory<>("title"));
        pastAppointmentDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
        pastAppointmentLocation.setCellValueFactory(new PropertyValueFactory<>("location"));
        pastAppointmentContact.setCellValueFactory(new PropertyValueFactory<>("contactID"));
        pastAppointmentType.setCellValueFactory(new PropertyValueFactory<>("type"));
        pastAppointmentStart.setCellValueFactory(new PropertyValueFactory<>("start"));
        pastAppointmentEnd.setCellValueFactory(new PropertyValueFactory<>("end"));
        pastAppointmentCustomerID.setCellValueFactory(new PropertyValueFactory<>("customerID"));
        pastAppointmentUserID.setCellValueFactory(new PropertyValueFactory<>("userID"));
    }
    /**
     * This function checks how many appointments match the chosen type and month
     * */
    public void onAppointmentType(ActionEvent actionEvent) {
        String type = String.valueOf(appointmentTypeComboBox.getValue());
        String month = String.valueOf(appointmentMonthComboBox.getValue());
        System.out.println(type);
        System.out.println(month);
        int totalCount = DBAppointment.getTypeAndMonthCount(type,month);
        countLabel.setText("Count: " + totalCount);
    }
    /**
     * This function checks how many appointments match the chosen type and month
     * */
    public void onAppointmentMonth(ActionEvent actionEvent) {
        String type = String.valueOf(appointmentTypeComboBox.getValue());
        String month = String.valueOf(appointmentMonthComboBox.getValue());
        int totalCount = DBAppointment.getTypeAndMonthCount(type,month);
        countLabel.setText("Count: " + totalCount);
    }

    /**
     * This function checks how many appointments match the chosen contact
     **/
    public void onAppointmentContact(ActionEvent actionEvent) {
        ObservableList<Appointment> appointmentList = FXCollections.observableArrayList();
        Contact contacts= (Contact) appointmentContactComboBox.getValue();
        int contactID = contacts.getContactID();
        for(Appointment a : DBAppointment.getAllAppointments()){
            if(a.getContactID()==contactID){
                appointmentList.add(a);
            }
        }
        appointmentContactTable.setItems(appointmentList);
        appointment_ID.setCellValueFactory(new PropertyValueFactory<>("appointmentID"));
        title.setCellValueFactory(new PropertyValueFactory<>("title"));
        description.setCellValueFactory(new PropertyValueFactory<>("description"));
        location.setCellValueFactory(new PropertyValueFactory<>("location"));
        contact.setCellValueFactory(new PropertyValueFactory<>("contactID"));
        type.setCellValueFactory(new PropertyValueFactory<>("type"));
        start.setCellValueFactory(new PropertyValueFactory<>("start"));
        end.setCellValueFactory(new PropertyValueFactory<>("end"));
        customer_Id.setCellValueFactory(new PropertyValueFactory<>("customerID"));
        user_Id.setCellValueFactory(new PropertyValueFactory<>("userID"));
    }
}
