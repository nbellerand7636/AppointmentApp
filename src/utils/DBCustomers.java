package utils;

import Model.Customer;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;
/**
 * This class makes calls to the Db for customer info
 * */
public class DBCustomers {
    /**
     * This function gets all customers from the Db
     * @return customerList
     * */
    public static ObservableList<Customer>getALlCustomers(){
        ObservableList<Customer>customerList=FXCollections.observableArrayList();
        try{
            String sql ="Select * from customers";

            PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);

            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                int customerID = rs.getInt("Customer_ID");
                String customerName = rs.getString("Customer_Name");
                String address = rs.getString("Address");
                String postalCode=rs.getString("Postal_code");
                String number = rs.getString("Phone");
                int divisionID = rs.getInt("Division_ID");
                Customer c = new Customer(customerID,customerName,address,postalCode,number,divisionID);
                customerList.add(c);
            }
        }   catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return  customerList;
    }
    /**
     * This function adds a new customer to the DB
     * */
    public static void createCustomer(String customerName,String address,String postalCode,String number,int divisionID){
        try{
            String sqlti = "INSERT INTO customers VALUES(NULL,?,?,?,?,NOW(),'NB',NOW(),'NB',?)";

            PreparedStatement psti = JDBC.getConnection().prepareStatement(sqlti);


            psti.setString(1,customerName);
            psti.setString(2,address);
            psti.setString(3,postalCode);
            psti.setString(4,number);
            psti.setInt(5,divisionID);


            psti.execute();

        }   catch(SQLException ex){
            ex.printStackTrace();
        }
    }
    /**
     * This function updates a customer in the DB
     * */
    public static void modifyCustomer(int customerID, String customerName, String address, String postalCode, String number, int divisionID) {
        try{
            String sqlti = "UPDATE customers SET Customer_Name = ?,Address = ?, Postal_Code = ?, Phone = ?,Division_ID = ? WHERE Customer_ID = ?";

            PreparedStatement psti = JDBC.getConnection().prepareStatement(sqlti);


            psti.setString(1,customerName);
            psti.setString(2,address);
            psti.setString(3,postalCode);
            psti.setString(4,number);
            psti.setInt(5,divisionID);
            psti.setInt(6,customerID);
            psti.execute();

        }   catch(SQLException ex){
            ex.printStackTrace();
        }
    }
    /**
     * This function deletes a customer from the DB
     * @param customerID
     * */
    public static void deleteCustomer(int customerID){
        try{
            String sqlta="DELETE from appointments WHERE Customer_ID = ?";

            PreparedStatement psta = JDBC.getConnection().prepareStatement(sqlta);
            psta.setInt(1,customerID);

            psta.execute();
            String sqlti="DELETE from customers WHERE Customer_ID = ?";

            PreparedStatement psti = JDBC.getConnection().prepareStatement(sqlti);
            psti.setInt(1,customerID);

            psti.execute();
        }   catch(Exception e){
            e.printStackTrace();
        }
    }
}
