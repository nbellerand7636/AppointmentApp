package Controller;

import Model.Appointment;
import Model.Customer;
import Model.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import utils.DBAppointment;
import utils.DBCountry;
import utils.DBCustomers;
import utils.DBUser;

import java.io.IOException;
import java.net.URL;
import java.time.*;
import java.time.chrono.ChronoLocalDateTime;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.Objects;
import java.util.ResourceBundle;

/**
 * This class controls the Home Page
 * */
public class
HomeScreenController implements Initializable {
    public TableView CustomerTable;
    public TableColumn customerID;
    public TableColumn customerName;
    public TableColumn customerAddress;
    public TableColumn customerPostalCode;
    public TableColumn customerNumber;
    public TableColumn customerDivisionID;
    public TableView AppointmentTable;
    public TableColumn appointmentID;
    public TableColumn appointmentTitle;
    public TableColumn appointmentDescription;
    public TableColumn appointmentLocation;
    public TableColumn appointmentContact;
    public TableColumn appointmentType;
    public TableColumn appointmentStart;
    public TableColumn appointmentEnd;
    public TableColumn appointmentCustomerID;
    public TableColumn appointmentUserID;
    public Label upcomingappointmentsLabel;

    /**
     * This function initializes the Home Page and displays the customers and appointments
     * */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        CustomerTable.setItems(DBCustomers.getALlCustomers());
        customerID.setCellValueFactory(new PropertyValueFactory<>("id"));
        customerName.setCellValueFactory(new PropertyValueFactory<>("name"));
        customerAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        customerPostalCode.setCellValueFactory(new PropertyValueFactory<>("postalCode"));
        customerNumber.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));
        customerDivisionID.setCellValueFactory(new PropertyValueFactory<>("divisionID"));

        AppointmentTable.setItems(DBAppointment.getAllAppointments());
        appointmentID.setCellValueFactory(new PropertyValueFactory<>("AppointmentID"));
        appointmentTitle.setCellValueFactory(new PropertyValueFactory<>("Title"));
        appointmentDescription.setCellValueFactory(new PropertyValueFactory<>("Description"));
        appointmentLocation.setCellValueFactory(new PropertyValueFactory<>("Location"));
        appointmentType.setCellValueFactory(new PropertyValueFactory<>("Type"));
        appointmentStart.setCellValueFactory(new PropertyValueFactory<>("Start"));
        appointmentEnd.setCellValueFactory(new PropertyValueFactory<>("End"));
        appointmentCustomerID.setCellValueFactory(new PropertyValueFactory<>("CustomerID"));
        appointmentUserID.setCellValueFactory(new PropertyValueFactory<>("UserID"));
        appointmentContact.setCellValueFactory(new PropertyValueFactory<>("ContactID"));
        boolean noAppointment =true;
        ObservableList<Appointment> aList = DBAppointment.getAllAppointments();
        for (Appointment a :aList){
            LocalDateTime ldt = a.getStart();
            LocalDateTime currentTime = LocalDateTime.now();

            long timeDifference = ChronoUnit.MINUTES.between(currentTime,ldt);
            if(timeDifference <=15 && timeDifference > 0){
                noAppointment = false;
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Appointment Alert");
                alert.setHeaderText(null);
                alert.setContentText("There is an upcoming appointment !");

                alert.showAndWait();
                upcomingappointmentsLabel.setText("Appointment # " + a.getAppointmentID() + " Starts at " + ldt);
                upcomingappointmentsLabel.setVisible(true);
            }   else {
                upcomingappointmentsLabel.setVisible(true);
            }


//            System.out.println(timeDifference);
        }
        if(noAppointment) {
            System.out.println("There are no upcoming appointments");
        }
    }
    /**
     * This function loads the Add Customer Screen
     * **/
    public void onAddCustomerClicked(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/view/AddCustomerScreen.fxml")));
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root, 900, 700);
        stage.setTitle("Home Page");
        stage.setScene(scene);
        stage.show();
    }
    /**
     * This function takes you to the Modify Customer Screen for the selected customer
     * */
    public void onUpdateCustomerClicked(ActionEvent actionEvent) throws IOException {
        Customer newData = (Customer)CustomerTable.getSelectionModel().getSelectedItem();

        ModifyCustomerController.sendProductData(newData);
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/view/ModifyCustomerScreen.fxml")));
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root, 900, 700);
        stage.setTitle("Home Page");
        stage.setScene(scene);
        stage.show();
    }
    /**
     * This function deletes the customer and their appointments.
     * */
    public void onDeleteClicked(ActionEvent actionEvent) {
        Customer newData = (Customer)CustomerTable.getSelectionModel().getSelectedItem();
        int deleteID= newData.getId();
        DBCustomers.deleteCustomer(deleteID);
        CustomerTable.setItems(DBCustomers.getALlCustomers());
        AppointmentTable.setItems(DBAppointment.getAllAppointments());
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information Dialog");
        alert.setHeaderText(null);
        alert.setContentText("The customer was deleted");

        alert.showAndWait();

    }
    /**
     * This function opens the New Appointment Screen.
     * */
    public void onNewApptClicked(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/view/AddAppointmentsScreen.fxml")));
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root, 900, 700);
        stage.setTitle("Home Page");
        stage.setScene(scene);
        stage.show();
    }
    /***
     * This function opens the update appointment screen for the selected appointment
     * */
    public void updateAppointmentClicked(ActionEvent actionEvent) throws IOException {
    Appointment selectedAppointment = (Appointment) AppointmentTable.getSelectionModel().getSelectedItem();
    ModifyAppointmentController.sendAppointmentData(selectedAppointment);

        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/view/ModifyAppointmentsScreen.fxml")));
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root, 900, 700);
        stage.setTitle("Home Page");
        stage.setScene(scene);
        stage.show();
    }
    /**
     * This function deletes the selected appointment
     * */
    public void deleteAppointmentClicked(ActionEvent actionEvent) {
        Appointment newAppt = (Appointment) AppointmentTable.getSelectionModel().getSelectedItem();
        int deleteID= newAppt.getAppointmentID();
        DBAppointment.deleteAppointment(deleteID);
        AppointmentTable.setItems(DBAppointment.getAllAppointments());
        upcomingappointmentsLabel.setText("Appointment # "+ newAppt.getAppointmentID()+ " of type: " + newAppt.getType()+" was canceled.");
    }
    /**
     * This function shows all appointments for the current month
     * I used a lambda to filter the appointments by month.
     * */
    public void onMonth(ActionEvent actionEvent) {
        ObservableList<Appointment>alist= DBAppointment.getAllAppointments();

        ObservableList<Appointment>monthList = alist.filtered(t->{
            Month m = Month.from(LocalDate.now());
               if(Month.from(t.getStart()).equals(m)){
                   return true;
               }
               return false;
        });
        AppointmentTable.setItems(monthList);
    }
    /**
     * This function shows all appointments
     * */
    public void onShowAll(ActionEvent actionEvent) {
        AppointmentTable.setItems(DBAppointment.getAllAppointments());

    }

    /**
     * This function shows all appointments for the next week
     * I used a lambda to filter the appointments by week
     * */
    public void onShowWeek(ActionEvent actionEvent) {
        LocalDateTime plusWeek = LocalDateTime.now().plusWeeks(1);
        ObservableList<Appointment>alist= DBAppointment.getAllAppointments();
        ObservableList<Appointment>weekList = alist.filtered(t->{
           if(t.getStart().isBefore(plusWeek) && t.getStart().isAfter(LocalDateTime.now())){
               return true;
           };
           return false;
        });
        AppointmentTable.setItems(weekList);
    }

    /**
     * This function loads the reports page
     * */
    public void onReportsClicked(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/view/Report.fxml")));
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root, 900, 700);
        stage.setTitle("Report Page");
        stage.setScene(scene);
        stage.show();
    }
}
