//#SELECT count(*) AS total FROM client_schedule.appointments WHERE monthname(Start)='May' AND Type = 'De-Briefing';
//        SELECT DISTINCT type from client_schedule.appointments
package utils;

import Model.Appointment;
import com.mysql.cj.jdbc.JdbcPreparedStatement;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Time;
import java.sql.Timestamp;
import java.time.LocalDateTime;
/**
 * This is the class that makes calls to the DB for Appointment info
 * */
public class DBAppointment {
    /**
     * This function counts the number of appointments with the given type and month
     * @param month
     * @param type
     * @return count
     * */
    public static int getTypeAndMonthCount(String type, String month){
        try{
            String sql = "SELECT count(*) AS total FROM appointments WHERE monthname(Start)= ? AND Type = ?";

            PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
            ps.setString(1,month);
            ps.setString(2,type);

            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                int count = rs.getInt("total");
                System.out.println(count);
                return count;
            }

        }   catch(Exception e){
            e.printStackTrace();
        }
        return 0;
    }
    /**
     * This function gets all the appointment types
     * @return typeList
     * */
    public static ObservableList<String> getAppointmentTypes(){
        ObservableList<String> typeList = FXCollections.observableArrayList();
        try {
            String sql = "SELECT DISTINCT type from appointments";

            PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);

            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                String type = rs.getString("type");
                typeList.add(type);
            }
        }   catch(Exception e){
            e.printStackTrace();
        }
        return typeList;
    }
    /**
     * This function gets all appointments in the DB
     * @return appointmentList
     * */
    public static ObservableList<Appointment> getAllAppointments(){
        ObservableList <Appointment> appointmentList = FXCollections.observableArrayList();
        try{
            String sql = "SELECT * FROM appointments";

            PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);

            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                int appointmentID = rs.getInt("Appointment_ID");
                String title = rs.getString("Title");
                String description = rs.getString("Description");
                String location = rs.getString("Location");
                String type = rs.getString("Type");
                LocalDateTime start = rs.getTimestamp("Start").toLocalDateTime();
                LocalDateTime end = rs.getTimestamp("End").toLocalDateTime();
                int customerID = rs.getInt("Customer_ID");
                int userID = rs.getInt("User_ID");
                int contactID = rs.getInt("Contact_ID");

                Appointment A = new Appointment(appointmentID,title,description,location,type,start,end,customerID,userID,contactID);
                appointmentList.add(A);

            }
        }   catch(Exception e){
                e.printStackTrace();
        }

        return  appointmentList;
    }
    /**
     * This function adds a new appointment to the DB
     * */
    public static void createAppointment(String title,String description,String location,String type,LocalDateTime start,LocalDateTime end,int customerID,int userID,int contactID){
        try{
            String sql = "INSERT INTO appointments VALUES(NULL,?,?,?,?,?,?,NOW(),'NB',NOW(),'NB',?,?,?)";

            PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);

            ps.setString(1,title);
            ps.setString(2,description);
            ps.setString(3,location);
            ps.setString(4,type);
            ps.setTimestamp(5, Timestamp.valueOf(start));
            ps.setTimestamp(6, Timestamp.valueOf(end));
            ps.setInt(7,customerID);
            ps.setInt(8,userID);
            ps.setInt(9,contactID);

            ps.execute();


        }   catch(Exception e){
            e.printStackTrace();
        }

    }
    /**
     * This function updates an appointment in the DB
     * */
    public static void updateAppointment(String title,String description,String location,int contactID,String type,LocalDateTime start,LocalDateTime end,int customerID,int appointmentID){
        try{
            String sql = "UPDATE appointments SET Title=?,Description =?,Location =?,Contact_ID = ?,Type=?,Start=?,End=?,Customer_ID=? WHERE Appointment_ID =?";

            PreparedStatement psti = JDBC.getConnection().prepareStatement(sql);


            psti.setString(1,title);
            psti.setString(2,description);
            psti.setString(3,location);
            psti.setInt(4,contactID);
            psti.setString(5,type);
            psti.setTimestamp(6,Timestamp.valueOf(start));
            psti.setTimestamp(7,Timestamp.valueOf(end));
            psti.setInt(8,customerID);
            psti.setInt(9,appointmentID);
            psti.execute();

        }   catch(Exception e){
                e.printStackTrace();
        }
    }
    /**
     * This function deletes an appointment from the DB
     * @param appointmentID
     * */
    public static void deleteAppointment(int appointmentID){
        try{
            String sqlti="DELETE from appointments WHERE Appointment_ID = ?";

            PreparedStatement psti = JDBC.getConnection().prepareStatement(sqlti);
            psti.setInt(1,appointmentID);

            psti.execute();
        }   catch(Exception e){
            e.printStackTrace();
        }

    }

}
