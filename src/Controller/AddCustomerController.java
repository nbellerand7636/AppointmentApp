//SELECT * FROM client_schedule.appointments WHERE month(start) = month(now());
package Controller;

import Model.Country;
import Model.Division;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import utils.DBCountry;
import utils.DBCustomers;
import utils.DBDivision;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

import static utils.DBCountry.*;

public class AddCustomerController implements Initializable {

    public TextField customerName;
    public TextField customerAddress;
    public TextField customerPostalCode;
    public TextField customerNumber;
    public TextField customerID;
    public ComboBox <Country> countryComboBox;
    public ComboBox <Division> divisionComboBox ;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        countryComboBox.setItems(DBCountry.getAllCountries());
        countryComboBox.setPromptText("Select a Country.");
//        divisionComboBox.setItems(DBDivision.getDivisions(1));


    }

    public void onSubmitClicked(ActionEvent actionEvent) throws IOException {
        String name = customerName.getText();
        String address = customerAddress.getText();
        String postalCode = customerPostalCode.getText();
        String number = customerNumber.getText();
        int divisionID = divisionComboBox.getValue().getDivisionID();
        DBCustomers.createCustomer(name,address,postalCode,number,divisionID);
//        Customer addCustomer = new Customer(id,name,address,postalCode,number,country,divisionID);
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/view/HomePage.fxml")));
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root, 900, 700);
        stage.setTitle("Home Page");
        stage.setScene(scene);
        stage.show();
    }

    public void onCancelClicked(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/view/HomePage.fxml")));
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root, 900, 700);
        stage.setTitle("Home Page");
        stage.setScene(scene);
        stage.show();
    }

    public void onDivisionCombo(ActionEvent actionEvent) {

    }

    public void onCountryCombo(ActionEvent actionEvent) {
        Country selectedCountry = countryComboBox.getValue();
        divisionComboBox.setItems(DBDivision.getDivisions(selectedCountry.getCountryID()));

    }
}
