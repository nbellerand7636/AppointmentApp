package Model;
/**
 * This is the User class
 * */
public class User {
    private int userid;
    private String userName;
    private String password;
    /**
     * This is the User constructor
     * */
    public User(int userid,String userName, String password){
    this.userid= userid;
    this.userName= userName;
    this.password = password;
    }
    /**
     * This function gets the user ID
     * @return userid
     * */
    public int getUserid() {
        return userid;
    }
    /**
     * This function gets the user name
     * @return userName
     * */
    public String getUserName() {
        return userName;
    }
    /**
     * This function gets the password
     * @return password
     * */
    public String getPassword() {
        return password;
    }
    /**
     * This function sets the user ID
     * @param userid
     */
    public void setUserid(int userid) {
        this.userid = userid;
    }
    /**
     * This function sets the username
     * @param userName
     * */
    public void setUserName(String userName) {
        this.userName = userName;
    }
    /**
     * This function sets the password
     * @param password
     * */
    public void setPassword(String password) {
        this.password = password;
    }
}
