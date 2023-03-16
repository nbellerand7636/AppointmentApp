package Model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
/**
 * This is the Customer class
 * */
public class Customer {
    private int id;
    private String name;
    private String address;
    private String postalCode;
    private String phoneNumber;
    private int divisionID;
    public static ObservableList<Customer>customerList=FXCollections.observableArrayList();
    /**
     * This is the customer constructor
     * */
    public Customer(int id,String name,String address,String postalCode,String phoneNumber,int divisionID){
        this.id = id;
        this.name = name;
        this.address = address; 
        this.postalCode = postalCode;
        this.phoneNumber = phoneNumber;
        this.divisionID= divisionID;

    }
    /**
     * This function gets the ID
     * @return id
     * */
    public int getId() {
        return id;
    }
    /**
     * This function gets the customer name
     * @return name
     * */
    public String getName() {
        return name;
    }

    /**
     * This function gets the customer address
     * @return address
     * */
    public String getAddress() {
        return address;
    }

    /**
     * This function returns the customers postal code
     * @return postalCode
     * */
    public String getPostalCode() {
        return postalCode;
    }

    /**
     * This function returns the customers phone number
     * @return phoneNumber
     * */
    public String getPhoneNumber() {
        return phoneNumber;
    }

    /**
     * This function returns the division Id
     * @return divisionID
     * */
    public int getDivisionID(){return divisionID;}

    /**
     * This function sets the customers ID
     * @param id
     * */
    public void setId(int id) {
        this.id = id;
    }
    /**
     * This function sets the customers name
     * @param name
     * */
    public void setName(String name) {
        this.name = name;
    }
    /**
     * This function sets the customers address
     * @param address
     * */
    public void setAddress(String address) {
        this.address = address;
    }
    /**
     * This function sets the customers postal code
     * @param postalCode
     * */
    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }
    /**
     * This function sets the customers phone number
     * @param phoneNumber
     * */
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
    /**
     * This function sets the Division ID
     * @param divisionID
     * */
    public void setDivisionID(int divisionID){this.divisionID = divisionID;}
}
