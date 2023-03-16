package utils;

import Model.Appointment;
import Model.Contact;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
/**
 * This is the class that make calls to the DB for contact info
 * */
public class DBContact {
    /**
     * This function gets all the contacts from the DB
     * @return contactList
     * */
    public static ObservableList<Contact> getAllContacts() {
        ObservableList<Contact> contactList = FXCollections.observableArrayList();
        try {
            String sql = "Select * FROM contacts";

            PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                int contactID = rs.getInt("Contact_ID");
                String contactName = rs.getString("Contact_Name");
                String email = rs.getString("Email");
                Contact c= new Contact(contactID,contactName,email);
                contactList.add(c);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return contactList;
    }
}
