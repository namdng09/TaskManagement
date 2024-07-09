package dao;

import java.sql.*;
import model.Comment;

public class CommentDAO {

    public ResultSet getAllCommentByCardID(String cardID) throws SQLException {
        String query = "SELECT [CommentID], [User].[FirstName], [User].[LastName], [CreatedDate], CAST([Comment] AS NVARCHAR(MAX)) AS [Comment]\n"
                + "FROM [dbo].[Comment]\n"
                + "INNER JOIN [dbo].[User] ON [User].[User_UID] = [Comment].[User_UID]\n"
                + "WHERE [CardID] = ?\n"
                + "GROUP BY [CommentID], [User].[FirstName], [User].[LastName], [CreatedDate], CAST([Comment] AS NVARCHAR(MAX))\n"
                + "ORDER BY [CreatedDate] DESC;";
        PreparedStatement pstmt = createPreparedStatement(query);
        // modify the query here
        pstmt.setString(1, cardID);
        return executeQuery(pstmt);
    }

    public void insertComment(Comment comment, String cardID, String user_UID) throws SQLException {
        String query = "INSERT INTO [dbo].[Comment] ([CommentID], [CardID], [User_UID], [CreatedDate], [Comment]) VALUES (?, ?, ?, ?, ?)";
        PreparedStatement pstmt = createPreparedStatement(query);

        pstmt.setString(1, comment.getCommentID());
        pstmt.setString(2, cardID);
        pstmt.setString(3, user_UID);
        pstmt.setTimestamp(4, comment.getCreateDate());
        pstmt.setString(5, comment.getComment());

        pstmt.executeUpdate();
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
