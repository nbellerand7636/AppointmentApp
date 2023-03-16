package Model;

import java.time.LocalDateTime;
/**
 * This is the Appointment class
 * */
public class Appointment {

    private final int appointmentID;
    private final String title;
    private final String description;
    private final String location;
    private final String type;
    private final LocalDateTime start;
    private final LocalDateTime end;
    private final int customerID;
    private final int userID;
    private final int contactID;
    /**
     * This is the constructor for the Appointment class
     * */
    public Appointment(int appointmentID, String title, String description, String location, String type, LocalDateTime start, LocalDateTime end, int customerID, int userID, int contactID) {
        this.appointmentID = appointmentID;
        this.title = title;
        this.description = description;
        this.location = location;
        this.type = type;
        this.start = start;
        this.end=end;
        this.customerID = customerID;
        this.userID = userID;
        this.contactID = contactID;
    }

    /**
     * This method gets the appointment Id
     * @return appointmentID
     * */
    public int getAppointmentID() {
        return appointmentID;
    }
    /**
     * This method gets the title
     * @return title
     * */
    public String getTitle() {
        return title;
    }
    /**
     * This method gets the description
     * @return description
     * */
    public String getDescription() {
        return description;
    }
    /**
     * This method gets the location
     * @return location
     * */
    public String getLocation() {
        return location;
    }
    /**
     * This method gets the type
     * @return type
     * */
    public String getType() {
        return type;
    }
    /**
     * This method gets the start time
     * @return start
     * */
    public LocalDateTime getStart() {
        return start;
    }
    /**
     * This method gets the end time
     * @return end
     * */
    public LocalDateTime getEnd() {
        return end;
    }
    /**
     * This method gets the customer ID
     * @return customerID
     * */
    public int getCustomerID() {
        return customerID;
    }
    /**
     * This method gets the contact ID
     * @return contactID
     * */
    public int getContactID() {
        return contactID;
    }
    /**
     * This method gets the User ID
     * @return userID
     * */
    public int getUserID() {
        return userID;
    }

}
