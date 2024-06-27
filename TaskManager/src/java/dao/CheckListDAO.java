package dao;

import java.sql.*;

public class CheckListDAO {

    public ResultSet getAllCheckListByCardID(String cardID) throws SQLException {
        String query = "SELECT * FROM [dbo].[CheckList] WHERE [CardID] = ?";
        PreparedStatement pstmt = this.createPreparedStatement(query);
        // modify the query here
        pstmt.setString(1, cardID);
        return executeQuery(pstmt);
    }

    public void deleteCheckList(String checkListID) throws SQLException {
        String query = "DELETE FROM [dbo].[CheckList] WHERE [CheckListID] = ?";
        PreparedStatement pstmt = this.createPreparedStatement(query);
        // modify the query here
        pstmt.setString(1, checkListID);
        pstmt.executeUpdate();
    }

    public void updateNameCheckList(String checkListID, String name) throws SQLException {
        String query = "UPDATE [dbo].[CheckList] SET [Name] = ? WHERE [CheckListID] = ?";
        PreparedStatement pstmt = this.createPreparedStatement(query);
        // modify the query here
        pstmt.setString(1, name);
        pstmt.setString(2, checkListID);

        pstmt.executeUpdate();
    }

    public ResultSet getLastestCheckListID() throws SQLException {
        String query = "SELECT [CheckListID] FROM [dbo].[CheckList] ORDER BY [CheckListID] DESC LIMIT 1";
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
