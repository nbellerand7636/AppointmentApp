package utils;

import Model.User;
import javafx.collections.ObservableList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
/**
 * This class gets info about Users from the DB
 * */
public class DBUser {
    /**
     * This function gets a specific user from the DB
     * @param username
     * @return user
     * */
    public static User getUser(String username){
        try{
            String sql = "SELECT User_ID,User_Name,Password FROM users WHERE User_Name = ?";

            PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
            ps.setString(1,username);

            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                int userID = rs.getInt("User_ID");
                String userName = rs.getString("User_name");
                String passWord = rs.getString("Password");
                User user = new User(userID,userName,passWord);
                return user;
            }
        }   catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
