package dao;

import java.sql.*;
import model.ListTask;

public class ListTaskDAO {

    public void insertListTask(ListTask listTask, String boardID) throws SQLException {
        String query = "INSERT INTO [dbo].[ListTask] ([ListTaskID],[BoardID],[Name])"
                + "VALUES (?,?,?)";
        PreparedStatement pstmt = createPreparedStatement(query);
        pstmt.setString(1, listTask.getListTaskID());
        pstmt.setString(2, boardID);
        pstmt.setString(3, listTask.getListTaskName());

        pstmt.executeUpdate();
    }

    public void deleteListTask(String listTaskID) throws SQLException {
        String query = "DELETE FROM CheckList WHERE CardID IN (SELECT CardID FROM Card WHERE ListTaskID = ?); " +
                   "DELETE FROM Comment WHERE CardID IN (SELECT CardID FROM Card WHERE ListTaskID = ?); " +
                   "DELETE FROM Card WHERE ListTaskID = ?; " +
                   "DELETE FROM ListTask WHERE ListTaskID = ?;";
        PreparedStatement pstmt = createPreparedStatement(query);
        // modify the query here
        pstmt.setString(1, listTaskID);
        pstmt.setString(2, listTaskID);
        pstmt.setString(3, listTaskID);
        pstmt.setString(4, listTaskID);
        pstmt.executeUpdate();
    }

    public void renameListTask(String listTaskID, String newName) throws SQLException {
        String query = "UPDATE [dbo].[ListTask] SET [Name] = ? WHERE [ListTaskID] = ?";
        PreparedStatement pstmt = createPreparedStatement(query);
        // modify the query here
        pstmt.setString(1, newName);
        pstmt.setString(2, listTaskID);
        pstmt.executeUpdate();
    }

    public ResultSet getAllListTaskByBoardID(String boardID) throws SQLException {
        String query = "SELECT * FROM [dbo].[ListTask] WHERE [BoardID] = ?";
        PreparedStatement pstmt = createPreparedStatement(query);
        // modify the query here
        pstmt.setString(1, boardID);
        return executeQuery(pstmt);
    }

    public ResultSet getLastestListTaskID() throws SQLException {
        String query = "SELECT TOP 1 [ListTaskID] FROM [dbo].[ListTask] ORDER BY [ListTaskID] DESC";
        PreparedStatement pstmt = createPreparedStatement(query);
        // modify the query here
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
