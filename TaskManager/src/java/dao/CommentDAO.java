package dao;

import java.sql.*;

public class CommentDAO {

    public ResultSet getAllCommentByCardID(String cardID) throws SQLException {
        String query = "SELECT [CommentID],[User].[FirstName],[User].[LastName],[CreatedDate],[Comment]"
                + "FROM [dbo].[Comment]"
                + "INNER JOIN [dbo].[User] ON [User].[User_UID] = [Comment].[User_UID]"
                + "WHERE [CardID] = ?"
                + "GROUP BY [CommentID],[User].[FirstName],[User].[LastName],[Comment]";
        PreparedStatement pstmt = createPreparedStatement(query);
        // modify the query here
        pstmt.setString(1, cardID);
        return executeQuery(pstmt);
    }

    public void deleteComment(String commentID) throws SQLException {
        String query = "DELETE FROM [dbo].[Comment] WHERE [CommentID] = ?";
        PreparedStatement pstmt = this.createPreparedStatement(query);
        // modify the query here
        pstmt.setString(1, commentID);

        pstmt.executeUpdate();
    }

    public void updateComment(String commentID, String comment) throws SQLException {
        String query = "UPDATE [dbo].[Comment] SET [Comment] = ? WHERE [CommentID] = ?";
        PreparedStatement pstmt = this.createPreparedStatement(query);
        // modify the query here
        pstmt.setString(1, comment);
        pstmt.setString(2, commentID);

        pstmt.executeUpdate();
    }

    public ResultSet getLastestCommentID() throws SQLException {
        String query = "SELECT TOP 1 [CommentID] FROM [dbo].[Comment] ORDER BY [CommentID] DESC";
        PreparedStatement pstmt = this.createPreparedStatement(query);
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
