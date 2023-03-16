package utils;

import Model.Division;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
/**
 * This class makes calls to the DB for Division info
 * */
public class DBDivision {
    /**
     * This function returns all divisions with the given countryID
     * @param countryID
     * @return divisionList
     * */
    public static ObservableList<Division> getDivisions(int countryID){
        ObservableList<Division> divisionList = FXCollections.observableArrayList();
        try{
            String sql = "Select Division_ID,Division,Country_ID FROM first_level_divisions WHERE Country_ID = ?";

            PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);

            ps.setInt(1,countryID);

            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                int divisionID = rs.getInt("Division_ID");
                String divisionName = rs.getString("Division");
                int country = rs.getInt("Country_ID");
                Division d = new Division(divisionID,divisionName,country);
                divisionList.add(d);
            }

        }   catch(Exception e){
            e.printStackTrace();
        }
        return divisionList;
    }
    /**
     * This function gets a specific country from the DB
     * @param number
     * @param country
     * @return d
     * */
    public static Division getSpecificCountry(int number, int country){
        for(Division d:getDivisions(country)){
            if(d.getDivisionID()==number){
                return d;
            }
        }
        return null;
    }
}
