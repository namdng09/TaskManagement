package utility;


import java.util.UUID;

/**
 * Utility class providing various helper methods.
 * 
 * This class includes a method to generate a unique User UID using UUID.
 * 
 * @author namdng09
 */
public class Utility {
    
    /**
     * Generates a unique User UID using UUID.
     * 
     * @return The generated User UID as a String.
     */
    public String generateUserUID() {
        // Generate a random UUID (Universally Unique Identifier)
        UUID uuid = UUID.randomUUID();
        
        // Convert UUID to a string
        String userUID = uuid.toString();
        
        // Return the generated user UID
        return userUID;
    }
}
