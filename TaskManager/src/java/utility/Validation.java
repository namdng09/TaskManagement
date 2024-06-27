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
    
    private static final String USERNAME_REGEX = "^(?!-)(?!.*--)[a-zA-Z0-9-]{4,20}(?<!-)$";
    private static final String EMAIL_REGEX = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$";
    private static final String PASSWORD_REGEX = "^(?=.*[A-Z])(?=.*[a-z])(?=.*[0-9])(?=.*[@#$%^&*+=])(?=\\S+$).{8,}$";
    private static final String PHONE_REGEX = "^[\\d]{10}$";
    private static final String NAME_REGEX = "[\\w\\s]+";
    
    /**
     * Checks if the provided username has valid syntax.
     *
     * @param username the username to validate.
     * @return true if the username is valid, false otherwise.
     */
    public boolean isValidSyntaxUsername(String username) {
        // Username must not start or end with a hyphen and contain multiple hyphens
        // Only allows alphanumeric characters and single hyphens
        // Length of username should be min = 4, max = 20
        Pattern pattern = Pattern.compile(USERNAME_REGEX);
        Matcher matcher = pattern.matcher(username);
        return matcher.matches();
    }
    
    /**
     * Checks if the provided email has valid syntax.
     *
     * @param email the email to validate.
     * @return true if the email is valid, false otherwise.
     */
    public boolean isValidSyntaxEmail(String email) {
        // Email must start with alphanumeric characters or allowed special characters.
        // Must contain a single '@' symbol.
        // Must have a valid domain with at least one dot.
        // The top-level domain must be at least 2 characters long.
        Pattern pattern = Pattern.compile(EMAIL_REGEX);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    /**
     * Checks if the provided password has valid syntax.
     *
     * @param password the password to validate.
     * @return true if the password is valid, false otherwise.
     */
    public boolean isValidSyntaxPassword(String password) {
        // Password must be at least 8 characters long.
        // Must not contain space characters.
        // Must contain at least one digit, one letter, and one special character.
        Pattern pattern = Pattern.compile(PASSWORD_REGEX);
        Matcher matcher = pattern.matcher(password);
        return matcher.matches();
    }
    
    /**
     * Checks if the provided phone number has valid syntax.
     *
     * @param phoneNumber
     * @return true if the phone number is valid, false otherwise.
     */
    public boolean isValidSyntaxPhoneNumber(String phoneNumber) {
        // Phone number must be containt 10 digits.
        Pattern pattern = Pattern.compile(PHONE_REGEX);
        Matcher matcher = pattern.matcher(phoneNumber);
        return matcher.matches();
    }

    /**
     * Checks if the provided name string has valid syntax.
     *
     * @param name
     * @return true if the name is valid, false otherwise.
     */
    public boolean isValidSyntaxNameString(String name) {
        // Name of user can not have special character.
        Pattern pattern = Pattern.compile(NAME_REGEX);
        Matcher matcher = pattern.matcher(name);
        return matcher.matches();
    }
}

