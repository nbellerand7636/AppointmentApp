package Model;
/**
 * This is the Contact class
 * */
public class Contact {
    private int contactID;
    private String contactName;
    private String email;
    /**
     * This is the contact constructor
     * **/
    public Contact(int contactID, String contactName, String email) {
        this.contactID=contactID;
        this.contactName=contactName;
        this.email=email;
    }
    /**
     * This function gets the contact ID
     * @return contactID
     * */
    public int getContactID() {
        return contactID;
    }
    /**
     * This function gets the contact name
     * @return contactName
     * */
    public String getContactName() {
        return contactName;
    }
    /**
     * This function returns the email
     * @return email
     * */
    public String getEmail() {
        return email;
    }
    /**
     * This function sets the contactID
     * @return contactID
     * */
    public void setContactID(int contactID) {
        this.contactID = contactID;
    }
    /**
     * This function sets the contactName
     * */
    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    /**
     * This function sets the email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * This function returns the contact ID and Name in a string.
     *
     * */
    @Override   public String toString(){

        return contactID +" )" + contactName;
 }
}
