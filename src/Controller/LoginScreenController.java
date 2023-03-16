package Controller;

import Model.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import utils.DBUser;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Locale;
import java.util.Objects;
import java.util.ResourceBundle;
/**
 * This class handles the implementation of the logic for the Login Screen.
 * */
public class LoginScreenController implements Initializable {
    public TextField userName;
    public TextField passWord;
    public Label incorrectPWLabel;
    public Label localZone;
    public Label usernameLabel;
    public Label passwordLabel;
    public Button loginButton;
    public static User theUser;

    ResourceBundle rb = ResourceBundle.getBundle("Nat", Locale.getDefault());


    /**
     * @param user
     * This function sets the User information after logging in
     * */
    public static void sendUser(User user){
         theUser = user;
    }

    /**
     * This function initializes the Login Screen
     * */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ZoneId zone = ZoneId.systemDefault();
        localZone.setText("User Location: " + zone );

        if(Locale.getDefault().getLanguage().equals("fr") || Locale.getDefault().getLanguage().equals("en"))
        {
            usernameLabel.setText(rb.getString("username"));
            passwordLabel.setText(rb.getString("password"));
            loginButton.setText(rb.getString("login"));
            localZone.setText(rb.getString("location") +": " +zone);

        }
    }
    /**
     * This function checks the login credentials, then logs the results to the txt file and
     * goes to the home page
     * */
    public void onLoginButtonClicked(ActionEvent actionEvent) throws IOException {

        try {
            String user = userName.getText();

            String pw = passWord.getText();
            User newUser = DBUser.getUser(user);

            String filename = "login_activity.txt";
            FileWriter fwriter = new FileWriter(filename,true);

            PrintWriter outputFile = new PrintWriter(fwriter);

            if (newUser == null) {
                String item = user + " "+ LocalDateTime.now() + " login was not successful";
                outputFile.println(item);
                outputFile.close();
                incorrectPWLabel.setText(rb.getString("udeError"));
                incorrectPWLabel.setVisible(true);
            }   else{
                    if (pw.equals(newUser.getPassword())) {
                        String item = user + " "+ LocalDateTime.now() + " login was successful";
                        outputFile.println(item);
                        outputFile.close();
                        LoginScreenController.sendUser(newUser);
                        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/view/HomePage.fxml")));
                        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
                        Scene scene = new Scene(root, 900, 700);
                        stage.setTitle("Home Page");
                        stage.setScene(scene);
                        stage.show();


                    } else {
                        incorrectPWLabel.setVisible(true);
                        incorrectPWLabel.setText(rb.getString("pwError"));

                    }
            }
        }   catch(Exception e){

        }

    }

}
