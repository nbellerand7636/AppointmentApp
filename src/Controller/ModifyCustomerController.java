package Controller;

import Model.Appointment;
import Model.Country;
import Model.Customer;
import Model.Division;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import utils.DBCountry;
import utils.DBCustomers;
import utils.DBDivision;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;
/**
 * This class implements the logic for the Modify Customer Screen
 * */
public class ModifyCustomerController implements Initializable {

    public TextField customerName;
    public Label customerNameLabel;
    public Label customerAddressLabel;
    public TextField customerAddress;
    public TextField customerID;
    public Label customerIDLabel;
    public Label divisionIDLabel;
    public ComboBox <Division> divisionComboBox;
    public ComboBox country;
    public Label countryLabel;
    public Label numberLabel;
    public Label customerPostalCodeLabel;
    public TextField customerPostalCode;
    public TextField customerNumber;
    public ComboBox <Country> countryComboBox;
    public static Customer theData;

    /**
     * @param newData
     * This function gets the customer to be modified
     * */
    public static void sendProductData(Customer newData) {
        theData = newData;
    }

    /**
     * This function initializes the Modify Customer Controller
     * */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        countryComboBox.setItems(DBCountry.getAllCountries());
        Country selectedCountry = null;

        if(theData.getDivisionID()<55){
            selectedCountry= DBCountry.getSpecificCountry(1);
            countryComboBox.setValue(selectedCountry);
            Division d = DBDivision.getSpecificCountry(theData.getDivisionID(),1);
            divisionComboBox.setValue(d);
        }   else if(theData.getDivisionID()>55 & theData.getDivisionID()<100){
            selectedCountry= DBCountry.getSpecificCountry(3);
            countryComboBox.setValue(selectedCountry);
            Division d = DBDivision.getSpecificCountry(theData.getDivisionID(),3);
            divisionComboBox.setValue(d);
        }   else{
            selectedCountry= DBCountry.getSpecificCountry(2);
            countryComboBox.setValue(selectedCountry);
            Division d = DBDivision.getSpecificCountry(theData.getDivisionID(),2);
            divisionComboBox.setValue(d);
        }
        Country updatedCountry = countryComboBox.getValue();
        divisionComboBox.setItems(DBDivision.getDivisions(updatedCountry.getCountryID()));
        customerID.setText(String.valueOf(theData.getId()));
        customerName.setText(theData.getName());
        customerAddress.setText(theData.getAddress());
        customerPostalCode.setText(theData.getPostalCode());
        customerNumber.setText(theData.getPhoneNumber());

    }
    /**
     * This function updates the customer and returns to the Home Screen.
     * */
    public void onSubmitClicked(ActionEvent actionEvent) throws IOException {
        int id = Integer.parseInt(customerID.getText());
        String name = customerName.getText();
        String address = customerAddress.getText();
        String postalCode = customerPostalCode.getText();
        String number = customerNumber.getText();
        int divisionID = divisionComboBox.getValue().getDivisionID();
        DBCustomers.modifyCustomer(id,name,address,postalCode,number,divisionID);
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/view/HomePage.fxml")));
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root, 900, 700);
        stage.setTitle("Home Page");
        stage.setScene(scene);
        stage.show();
    }

    /**
     * This function cancels and returns to the Home Screen
     * */
    public void onCancelClicked(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/view/HomePage.fxml")));
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root, 900, 700);
        stage.setTitle("Home Page");
        stage.setScene(scene);
        stage.show();
    }
    /**
     * This function displays the chosen country in the combo box
     * */
    public void onCountryClicked(ActionEvent actionEvent) {
        Country updatedCountry = countryComboBox.getValue();
        divisionComboBox.setItems(DBDivision.getDivisions(updatedCountry.getCountryID()));
    }
}
