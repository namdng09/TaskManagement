package dao;

import java.sql.*;
import model.CheckList;

public class CheckListDAO {

    public void insertCheckList(CheckList checkList, String cardID) throws SQLException{
        String query = "INSERT INTO [dbo].[CheckList]"
                + "([CheckListID],[CardID],[Title],[isChecked])"
                + "VALUES (?,?,?,?)";
        PreparedStatement pstmt = createPreparedStatement(query);
        pstmt.setString(1, checkList.getCheckListID());
        pstmt.setString(2, cardID);
        pstmt.setString(3, checkList.getCheckListTitle());
        pstmt.setBoolean(4, checkList.isChecked());

        pstmt.executeUpdate();
    }

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

    public void updateCheckListTitle(String checkListID, String title) throws SQLException {
        String query = "UPDATE [dbo].[CheckList] SET [Title] = ? WHERE [CheckListID] = ?";
        PreparedStatement pstmt = this.createPreparedStatement(query);
        // modify the query here
        pstmt.setString(1, title);
        pstmt.setString(2, checkListID);

        pstmt.executeUpdate();
    }

    public void updateCheckListChecked(String checkListID, boolean checked) throws SQLException {
        String query = "UPDATE [dbo].[CheckList] SET [isChecked] = ? WHERE [CheckListID] = ?";
        PreparedStatement pstmt = this.createPreparedStatement(query);
        // modify the query here
        pstmt.setBoolean(1, checked);
        pstmt.setString(2, checkListID);

        pstmt.executeUpdate();
    }

    public ResultSet getLastestCheckListID() throws SQLException {
        String query = "SELECT TOP 1 [CheckListID] FROM [dbo].[CheckList] ORDER BY [CheckListID] DESC";
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
