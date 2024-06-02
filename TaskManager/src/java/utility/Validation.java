/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utility;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Utility class for validating user input such as usernames, emails, and passwords.
 * 
 * Contains methods to check the syntax of these inputs according to predefined rules.
 * 
 * @author namdng09
 */
public class Validation {
    
    /**
     * Checks if the provided username has valid syntax.
     *
     * @param username the username to validate.
     * @return true if the username is valid, false otherwise.
     */
    public boolean isValidSyntaxUsername(String username) {
        boolean flag = true;
        // username do not start-end with the hyphen and contain multiple hyphen
        // Only allows alphanumberic characters and single hyphen
        // Length of username should be min = 4, max = 20
        String regex = "^(?!-)(?!.*--)[a-zA-Z0-9-]{4,20}(?<!-)$";
        
        Pattern pattern = Pattern.compile(regex);
        
        Matcher matcher = pattern.matcher(username);
        
        if (!matcher.matches()) {
            flag = false;
        }
        return flag;
    }
    
    /**
     * Checks if the provided email has valid syntax.
     *
     * @param email the email to validate.
     * @return true if the email is valid, false otherwise.
     */
    public boolean isValidSyntaxEmail(String email) {
        boolean flag = true;
        // Starts with alphanumeric characters or allowed special characters.
        // Contains a single '@' symbol.
        // Has a valid domain with at least one dot.
        // The top level domain must be at least 2 characters long.
        String regex = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$";
        
        Pattern pattern = Pattern.compile(regex);
        
        Matcher matcher = pattern.matcher(email);
        
        if (!matcher.matches()) {
            flag = false;
        }
        return flag;
    }

    /**
     * Checks if the provided password has valid syntax.
     *
     * @param password the password to validate.
     * @return true if the password is valid, false otherwise.
     */
    public boolean isValidSyntaxPassword(String password) {
        boolean flag = true;
        // The password must be at least 8 characters long
        // Ensure there are no space characters
        // password must contain digit, letter, special characters.
        String regex = "^(?=.*[A-Z])(?=.*[a-z])(?=.*[0-9])(?=.*[@#$%^&*+=])(?=\\S+$).{8,}$";
        
        Pattern pattern = Pattern.compile(regex);
        
        Matcher matcher = pattern.matcher(password);
        
        if (!matcher.matches()) {
            flag = false;
        }
        return flag;
    }
}
