package dao;

import java.sql.*;
import model.Broad;

public class BroadDAO {

    public void insertBroad(Broad broad, String useruid) throws SQLException {
        String query = "INSERT INTO [dbo].[Broad]"
                + "([BroadID],[User_UID],[Name],[CreatedDate],[isPublic],[image])"
                + "VALUES (?,?,?,?,?,?)";
        PreparedStatement pstmt = createPreparedStatement(query);
        pstmt.setString(1, broad.getBroadID());
        pstmt.setString(2, useruid);
        pstmt.setString(3, broad.getBroadName());
        pstmt.setDate(4, broad.getCreateDate());
        pstmt.setBoolean(5, broad.isIsPublic());
        pstmt.setString(6, broad.getImage());

        pstmt.executeUpdate();
    }

    public void deleteBroad(String broadID) throws SQLException {
        String query = "DELETE FROM [dbo].[Broad] WHERE [BroadID] = ?";
        PreparedStatement pstmt = this.createPreparedStatement(query);
        pstmt.setString(1, broadID);

        pstmt.executeUpdate();
    }

    public void renameBroad(String broadID, String newName) throws SQLException {
        String query = "UPDATE [dbo].[Broad] SET [Name] = ? WHERE [BroadID] = ?";
        PreparedStatement pstmt = this.createPreparedStatement(query);
        pstmt.setString(1, newName);
        pstmt.setString(2, broadID);

        pstmt.executeUpdate();
    }

    public ResultSet getAllBroadByUserUID(String useruid) throws SQLException {
        String query = "SELECT * FROM [dbo].[Broad] WHERE [USER_ID] = ?";
        PreparedStatement pstmt = createPreparedStatement(query);
        // modify the query here
        pstmt.setString(1, useruid);
        return executeQuery(pstmt);
    }

    public ResultSet getAllBroadByName(String partOfName) throws SQLException {
        String query = "SELECT * FROM [dbo].[Broad] WHERE [Name] LIKE ?";
        PreparedStatement pstmt = this.createPreparedStatement(query);
        // modify the query here
        pstmt.setString(1, "%" + partOfName + "%");
        return executeQuery(pstmt);
    }

    public void toggleIsPublic(String broadID) throws SQLException {
        String query = "UPDATE [dbo].[Broad] SET [isPublic] = NOT [isPublic] WHERE [BroadID] = ?";
        PreparedStatement pstmt = this.createPreparedStatement(query);
        pstmt.executeUpdate();
    }

    public ResultSet getAllBroadByID(String broadID) throws SQLException {
        String query = "SELECT * FROM [dbo].[Broad] WHERE [BroadID] = ?";
        PreparedStatement pstmt = this.createPreparedStatement(query);
        // modify the query here
        pstmt.setString(1, broadID);
        return executeQuery(pstmt);
    }

    public ResultSet getLastestBroadID() throws SQLException {
        String query = "SELECT [BroadID] FROM [dbo].[Broad] ORDER BY [BroadID] DESC LIMIT 1";
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
