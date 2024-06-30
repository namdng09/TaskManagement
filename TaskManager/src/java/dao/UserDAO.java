package dao;

import java.sql.*;
import model.User;

/**
 * Data Access Object (DAO) for managing User operations in the database.
 * Extends DBContext to inherit database connection management.
 *
 * This class provides methods to insert, delete, retrieve, and check the
 * existence of users in the database.
 *
 * @author namdng09
 */
public class UserDAO {

    /**
     * Inserts a new user into the database.
     *
     * @param user The User object to insert.
     * @throws java.sql.SQLException
     */
    public void insertUser(User user) throws SQLException {
        String query = "INSERT INTO [dbo].[User]"
                + "([User_UID],[UserName],[Email],[Password],[FirstName],[LastName],[BirthDate],[Phone])"
                + "VALUES (?,?,?,?,?,?,?,?)";
        PreparedStatement pstmt = createPreparedStatement(query);
        pstmt.setString(1, user.getUserUID());
        pstmt.setString(2, user.getUsername());
        pstmt.setString(3, user.getEmail());
        pstmt.setString(4, user.getPassword());
        pstmt.setString(5, user.getFirstName());
        pstmt.setString(6, user.getLastName());
        pstmt.setDate(7, user.getBirthDate());
        pstmt.setString(8, user.getPhoneNumber());

        pstmt.executeUpdate();
    }

    /**
     * Deletes a user from the database by username.
     *
     * @param username The username of the user to delete.
     * @throws java.sql.SQLException
     */
    public void deleteUserByUsername(String username) throws SQLException {
        String query = "DELETE FROM [dbo].[User] WHERE [UserName] = ?";

        PreparedStatement pstmt = createPreparedStatement(query);
        pstmt.setString(1, username);

        pstmt.executeUpdate();
    }

    public ResultSet getAllUserByPartOfname(String partOfName) throws SQLException {
        String query = "SELECT * FROM [dbo].[User]"
        + "WHERE [FirstName] LIKE ? OR [LastName] LIKE ?";
        PreparedStatement pstmt = this.createPreparedStatement(query);
        // modify the query here
        String searchPattern = "%" + partOfName + "%";
        pstmt.setString(1, searchPattern);
        pstmt.setString(2, searchPattern);
        return executeQuery(pstmt);
    }

    /**
     * Retrieves a user set from the database by username.
     *
     * @param username The username of the user to retrieve.
     * @return The User object retrieved from the database, or null if not
     * found.
     * @throws java.sql.SQLException
     */
    public ResultSet getUserByUsername(String username) throws SQLException {
        String query = "SELECT * FROM [dbo].[User] WHERE "
                + "[UserName] = ? ";
        PreparedStatement pstmt = createPreparedStatement(query);
        // modify the query here
        pstmt.setString(1, username);
        return executeQuery(pstmt);
    }

    /**
     * Retrieves a user set from the database by email.
     *
     * @param email The email of the user to retrieve.
     * @return The User object retrieved from the database, or null if not
     * found.
     * @throws java.sql.SQLException
     */
    public ResultSet getUserByEmail(String email) throws SQLException {
        String query = "SELECT * FROM [dbo].[User] WHERE "
                + "[Email] = ? ";
        PreparedStatement pstmt = createPreparedStatement(query);
        pstmt.setString(1, email);
        return executeQuery(pstmt);
    }

    /**
     * Create instance for PreparedStatement class
     *
     * @param query the SQL query to be prepared.
     *
     * @return a PreparedStatement object that can be used to execute the query
     * with specified parameters.
     *
     * @throws SQLException if a database access error occurs or the SQL
     * statement cannot be pre-compiled.
     */
    public PreparedStatement createPreparedStatement(String query) throws SQLException {
        Connection conn = DBContext.getInstance().getConnection();
        return conn.prepareStatement(query);
    }

    /**
     * Executes a SQL query using the provided PreparedStatement.
     *
     * @param pstmt the PreparedStatement to be executed.
     * @return the ResultSet obtained from executing the query.
     * @throws java.sql.SQLException
     */
    public ResultSet executeQuery(PreparedStatement pstmt) throws SQLException {
        return pstmt.executeQuery();
    }
}
