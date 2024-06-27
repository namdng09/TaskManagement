package model;

import dao.UserDAO;
import org.mindrot.jbcrypt.BCrypt;
import utility.Utility;
import utility.Validation;
import java.sql.*;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author namdng09
 */
public class User {

    private String userUID;
    private String username;
    private String email;
    private String password;
    private String firstName;
    private String lastName;
    private Date birthDate;
    private String phoneNumber;

    public User() {
    }

    public User(String userUID, String username, String email, String password, String firstName, String lastName, Date birthDate, String phoneNumber) {
        this.userUID = userUID;
        this.username = username;
        this.email = email;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.phoneNumber = phoneNumber;
    }

    public String getUserUID() {
        return userUID;
    }

    public void setUserUID(String userUID) {
        this.userUID = userUID;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public String toString() {
        return "User{" + "userUID=" + userUID + ", username=" + username + ", password=" + password + ", email=" + email + '}';
    }

    /**
     * Validates a username and checks if it already exists in the database.
     *
     * @param username The username to validate.
     * @throws Exception if the username syntax is invalid or the username
     * already exists.
     */
    public void checkValidUserName(String username) throws Exception {
        Validation validate = new Validation();

        String message;
        if (!validate.isValidSyntaxUsername(username)) {
            message = "Username is not valid!";
            throw new Exception(message);
        }
        if (this.isExistUserName(username)) {
            message = "Username is already existed!";
            throw new Exception(message);
        }
    }

    public void checkValidPhoneNumber(String phone) throws Exception {
        Validation validate = new Validation();
        String message;

        if (!validate.isValidSyntaxPhoneNumber(phone)) {
            message = "Phone number is not valid!";
            throw new Exception(message);
        }
    }

    /**
     * Validates an email and checks if it already exists in the database.
     *
     * @param email The email address to validate.
     * @throws Exception if the email syntax is invalid or the email already
     * exists.
     */
    public void checkValidEmail(String email) throws Exception {
        Validation validate = new Validation();

        String message;
        if (!validate.isValidSyntaxEmail(email)) {
            message = "Email is not valid!";
            throw new Exception(message);
        }
        if (this.isExistEmail(email)) {
            message = "Email is already existed!";
            throw new Exception(message);
        }
    }

    /**
     * Validates a password to ensure it meets the required syntax.
     *
     * @param password The password to validate.
     * @throws Exception if the password syntax is invalid.
     */
    public void checkValidPassword(String password) throws Exception {
        Validation validate = new Validation();
        String message;
        if (!validate.isValidSyntaxPassword(password)) {
            message = "Password is not valid!";
            throw new Exception(message);
        }
    }

    /**
     * Validates a name to ensure it meets the required syntax.
     *
     * @param name
     * @throws Exception if the password syntax is invalid.
     */
    public void checkValidNameString(String name) throws Exception {
        Validation validate = new Validation();
        String message;
        if (!validate.isValidSyntaxNameString(name)) {
            message = "Name can not have special word!";
            throw new Exception(message);
        }
    }
    /**
     * Registers a new user by validating the inputs and storing the user
     * information.
     *
     * @param username The username for the new user.
     * @param email The email address for the new user.
     * @param password The password for the new user.
     * @param firstName
     * @param lastName
     * @param birthDate
     * @param phoneNumber
     */
    public void registerUser(String username, String email, String password, String firstName, String lastName, Date birthDate, String phoneNumber) {
        Utility utils = new Utility();
        UserDAO userDAO = new UserDAO();

        String user_uid = utils.generateUserUID();
        String fName = utils.formatNameString(firstName);
        String lName = utils.formatNameString(lastName);

        // Using BCrypt to make hashing password convenient
        String hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt(13));

        try {
            User user = new User(user_uid, username, email, hashedPassword, fName, lName, birthDate, phoneNumber);

            userDAO.insertUser(user);
        } catch (SQLException e) {
            //TODO: handle exception
            System.out.println("ERROR: " + e.getMessage());
        }
    }

    public boolean checkValidLogin(String username, String password) {
        UserDAO dao = new UserDAO();
        boolean flag = false;
        try {
            ResultSet rs = dao.getUserByUsername(username);
            if (rs.next()) {
                String hashedPassword = rs.getString("Password");
                if (BCrypt.checkpw(password, hashedPassword)) {
                    flag = true;
                }
            }
        } catch (SQLException e) {
            //TODO: handle exception
                System.out.println("ERROR: " + e.getMessage());
            }
            return flag;
    }

    public User getAccountByUsername(String username) {
        UserDAO dao = new UserDAO();
        User account = null;
        try {
            ResultSet rs = dao.getUserByUsername(username);
            if (rs.next()) {
                String uid = rs.getString("User_UID");
                String userName = rs.getString("Username");
                String userEmail = rs.getString("Email");
                String userPassword = rs.getString("Password");
                String fName = rs.getString("FirstName");
                String lName = rs.getString("LastName");
                Date userBirthDate = rs.getDate("BirthDate");
                String userPhoneNumber = rs.getString("Phone");
                account = new User(uid, userName, userEmail, userPassword, fName, lName, userBirthDate, userPhoneNumber);
            }
        } catch (SQLException e) {
            //TODO: handle exception
            System.out.println("ERROR" + e.getMessage());
        }
        return account;
    }

    /**
     * Checks if a username exist in the database
     *
     * @param username The username to check for existence
     * @return true if the username exist, false otherwise.j
     */
    public boolean isExistUserName(String username) {
        UserDAO userDAO = new UserDAO();
        boolean flag = false;
        try {
            ResultSet rs = userDAO.getUserByUsername(username);
            if (rs.next()) {  // Mean have duplicate username
                flag = true;
            }
        } catch (SQLException e) {
            //TODO: handle exception
            System.out.println("ERROR: " + e.getMessage());
        }
        return flag;
    }

    /**
     * Checks if an email exists in the database.
     *
     * @param email The email address to check for existence.
     * @return true if the email exists, false otherwise.
     */
    public boolean isExistEmail(String email) {
        UserDAO userDAO = new UserDAO();
        boolean flag = false;
        try {
            ResultSet rs = userDAO.getUserByEmail(email);
            if (rs.next()) {  // Mean have duplicate email
                flag = true;
            }
        } catch (SQLException e) {
            //TODO: handle exception
            System.out.println("ERROR: " + e.getMessage());
        }
        return flag;
    }

}
