package Model;
/**
 * This is the Division class
 * */
public class Division {
    private int divisionID;
    private String divisionName;
    private int country;
    /**
     * This is the Division constructor
     * */
    public Division(int divisionID, String divisionName, int country) {
        this.divisionID = divisionID;
        this.divisionName = divisionName;
        this.country = country;
    }
    /**
     * This function gets the Division ID
     * @return divisionID
     * */
    public int getDivisionID() {
        return divisionID;
    }
    /**
     * This function gets the division name
     * @return divisionName
     * */
    public String getDivisionName(){
        return divisionName;
    }
    /**
     * this function gets the country name
     * @return country
     * */
    public int getCountry() {
        return country;
    }
    /**
     * @return country
     * This function sets the division ID
     * */
    public void setDivisionID(int divisionID) {
        this.divisionID = divisionID;
    }
    /**
     * @param divisionName
     * This function sets the Division Name
     * */
    public void setDivisionName(String divisionName) {
        this.divisionName = divisionName;
    }
    /**
     * This function sets the Country
     * @param country
     * */
    public void setCountry(int country) {
        this.country = country;
    }
    /**
     * This function returns the division ID and name in a string
     * */
    @Override
    public String toString(){
        return divisionID +" - " + divisionName;
    }
}
