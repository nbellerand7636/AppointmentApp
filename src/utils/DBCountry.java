package utils;

import Model.Country;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
/**
 * This class makes calls to the DB for Country info
 * */
public class DBCountry {
    /**
     * This function gets all countries from the DB
     * @return countryList
     * */
    public static ObservableList<Country> getAllCountries(){
        ObservableList<Country>countryList = FXCollections.observableArrayList();
        try{
            String sql = "SELECT Country_ID,Country FROM countries";

            PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);

            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                int countryID = rs.getInt("Country_ID");
                String countryName = rs.getString("Country");
                Country co = new Country(countryID,countryName);
                countryList.add(co);
            }
        }   catch(Exception e){
            e.printStackTrace();
        }
        return countryList;
    }
    /**
     * This function gets a specific country from the DB
     * @param number
     * @return c
     * */
    public static Country getSpecificCountry(int number){
        for(Country c:getAllCountries()){
            if(c.getCountryID()==number){
                return c;
            }
        }
        return null;
    }
}
