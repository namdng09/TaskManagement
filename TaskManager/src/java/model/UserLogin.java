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
public class UserLogin {
    private String userUID;
    private String username;
    private String email;
    private String password;

    public UserLogin() {
    }

    public UserLogin(String userUID, String username, String email, String password) {
        this.userUID = userUID;
        this.username = username;
        this.email = email;
        this.password = password;
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
        UserDAO dao = new UserDAO();

        String message;
        if (!validate.isValidSyntaxUsername(username)) {
            message = "username is not valid!";
            throw new Exception(message);
        }
        if (dao.isExistUserName(username)) {
            message = "username is already existed!";
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
        UserDAO dao = new UserDAO();

        String message;
        if (!validate.isValidSyntaxEmail(email)) {
            message = "Email is not valid!";
            throw new Exception(message);
        }
        if (dao.isExistUserName(email)) {
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
     * Registers a new user by validating the inputs and storing the user
     * information.
     *
     * @param username The username for the new user.
     * @param email The email address for the new user.
     * @param password The password for the new user.
     */
    public void registerUser(String username, String email, String password) {
        Utility utils = new Utility();
        UserDAO dao = new UserDAO();

        String user_uid = utils.generateUserUID();

        // Using BCrypt to make hashing password convenient
        String hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt(13));

        UserLogin user = new UserLogin(user_uid, username, email, hashedPassword);

        dao.insertUserLogin(user);
    }
    
    public boolean checkValidLogin(String username, String password) {
        UserDAO dao = new UserDAO();
        boolean flag = false;
        try {
            ResultSet rs = dao.getUserByUsername(username);
            if (rs.next()) {
                String hashedPassword = rs.getString("password");
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

    public UserLogin getAccountByUsername(String username) {
        UserDAO dao = new UserDAO();
        UserLogin acc = null;
        try {
            ResultSet rs = dao.getUserByUsername(username);
            if (rs.next()) {
                String uid = rs.getString("user_uid");
                String userName = rs.getString("username");
                String userEmail = rs.getString("email");
                String userPassword = rs.getString("password");
                acc = new UserLogin(uid, userName, userEmail, userPassword);
            }
        } catch (Exception e) {
            //TODO: handle exception
            System.out.println("ERROR" + e.getMessage());
        }
        return acc;
    }
}
