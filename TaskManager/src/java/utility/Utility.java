package utility;

import java.util.UUID;
import java.sql.*;
import java.time.LocalDateTime;

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

    public String formatNameString(String name) {
        StringBuilder stringBuilder = new StringBuilder();
        String words[] = name.split("\\s+");
        for (String word : words) {
            stringBuilder.append(word.substring(0, 1).toUpperCase()).append(word.substring(1).toLowerCase()).append(" ");
        }
        return stringBuilder.toString().trim();
    }

    public String generateID(String prefix, ResultSet lastestID) {
        String lastID = null;
        try {
            if (lastestID.next()) {
                lastID = lastestID.getString(1);
            }

            if (lastID == null || lastID.isEmpty()) {
                lastID = prefix + "0001";
            } else {
                int idNum = Integer.parseInt(lastID.substring(prefix.length()));
                idNum++;
                lastID = String.format("%s%04d", prefix, idNum);
            }
        } catch (SQLException e) {
            //TODO: handle exception
            System.out.println("ERROR: " + e.getMessage());
        }
        return lastID;
    }

    public Timestamp getCurrentDate() {
        LocalDateTime currentDateTime = LocalDateTime.now(); // Get current date and time
        Timestamp timestamp = Timestamp.valueOf(currentDateTime); // Convert to java.sql.Timestamp
        return timestamp;
    }

}
