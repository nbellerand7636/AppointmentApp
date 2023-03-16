package Model;
/***
 * This is the country class
 */
public class Country {
    private int countryID;
    private String countryName;

    /**
     * This is the country constructor
     * */
    public Country(int countryID,String countryName){
        this.countryID = countryID;
        this.countryName=countryName;
    }
    /**
     * This function gets the country ID
     * @return  countryID
     * */
    public int getCountryID() {
        return countryID;
    }

    /**
     * This function gets the country name
     * @return countryName
     * */
    public String getCountryName() {
        return countryName;
    }

    /**
     * This function sets the country ID
     * @return countryID
     * */
    public void setCountryID(int countryID) {
        this.countryID = countryID;
    }

    /**
     * This function sets the country name
     * @return countryName
     * */
    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }
    /**
     * This function returns the country Id and country name in a string.
     * */
    @Override
    public String toString(){
        return(Integer.toString(countryID)+") "+ countryName);
    }
}
