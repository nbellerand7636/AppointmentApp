package com.company;

import Model.Customer;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import utils.JDBC;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.Locale;
import java.util.ResourceBundle;
/**
 * This class contains the functions for starting and running the program.
 * */
public class Main  extends Application {
    /**
     * This function loads the login screen.
     * */
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/view/LoginScreen.fxml"));
        stage.setTitle("First Screen");
        stage.setScene(new Scene(root,800,600));
        stage.show();
    }
    /**
     * This is a method for to put test data into the customer table.
     * */
    public static void addTestData(){
        Customer Nelly = new Customer(0,"Nelly","5 Nellyville Ln","11209","490-343-2323",22);
        Customer.customerList.add(Nelly);

    }
    /**
     * This is the main method which runs the program
     * **/
    public static void main(String[] args) {
	// write your code here
        System.out.println("Hello world from VM!");
        JDBC.openConnection();
        addTestData();

        launch(args);
        JDBC.closeConnection();
    }


}
