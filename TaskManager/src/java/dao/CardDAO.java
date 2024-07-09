package dao;

import java.sql.*;
import model.Card;

public class CardDAO {

    public void insertCard(Card card, String listTaskID) throws SQLException {
        String query = "INSERT INTO [dbo].[Card]"
                + "([CardID],[ListTaskID],[Name],[Description],[CreatedDate])"
                + "VALUES (?,?,?,?,?)";
        PreparedStatement pstmt = createPreparedStatement(query);
        pstmt.setString(1, card.getCardID());
        pstmt.setString(2, listTaskID);
        pstmt.setString(3, card.getCardName());
        pstmt.setString(4, card.getDescription());
        pstmt.setTimestamp(5, card.getCreatedDate());

        pstmt.executeUpdate();
    }

    public ResultSet getAllCardByListTaskID(String listTaskID) throws SQLException {
        String query = "SELECT * FROM [dbo].[Card] WHERE [ListTaskID] = ?";
        PreparedStatement pstmt = createPreparedStatement(query);
        // modify the query here
        pstmt.setString(1, listTaskID);
        return executeQuery(pstmt);
    }

    public ResultSet getLastestCardID() throws SQLException {
        String query = "SELECT TOP 1 [CardID] FROM [dbo].[Card] ORDER BY [CardID] DESC";
        PreparedStatement pstmt = createPreparedStatement(query);
        // modify the query here
        return executeQuery(pstmt);
    }

    public void deleteCard(String cardID) throws SQLException {
        String query = "DELETE FROM [dbo].[CheckList] WHERE CardID = ?; "
                + "DELETE FROM [dbo].[Comment] WHERE CardID = ?; "
                + "DELETE FROM [dbo].[Card] WHERE [CardID] = ?;";
        PreparedStatement pstmt = createPreparedStatement(query);

        pstmt.setString(1, cardID);
        pstmt.setString(2, cardID);
        pstmt.setString(3, cardID);

        pstmt.executeUpdate();
    }

    public void updateCardName(String cardID, String name) throws SQLException {
        String query = "UPDATE [dbo].[Card] SET [Name] = ? WHERE [CardID] = ?";
        PreparedStatement pstmt = this.createPreparedStatement(query);
        // modify the query here
        pstmt.setString(1, name);
        pstmt.setString(2, cardID);

        pstmt.executeUpdate();
    }

    public void updateCardPosition(String cardID, String newListTaskID) throws SQLException {
        String query = "UPDATE [dbo].[Card] SET [ListTaskID] = ? WHERE [CardID] = ?";
        PreparedStatement pstmt = this.createPreparedStatement(query);
        // modify the query here
        pstmt.setString(1, newListTaskID);
        pstmt.setString(2, cardID);

        pstmt.executeUpdate();
    }

    public void updateCardDescription(String cardID, String description) throws SQLException {
        String query = "UPDATE [dbo].[Card] SET [Description] = ? WHERE [CardID] = ?";
        PreparedStatement pstmt = this.createPreparedStatement(query);
        // modify the query here
        pstmt.setString(1, description);
        pstmt.setString(2, cardID);

        pstmt.executeUpdate();
    }

    public void updateCardDueDate(String cardID, Timestamp dueDate) throws SQLException {
        String query = "UPDATE [dbo].[Card] SET [DueDate] = ? WHERE [CardID] = ?";
        PreparedStatement pstmt = this.createPreparedStatement(query);
        // modify the query here
        pstmt.setTimestamp(1, dueDate);
        pstmt.setString(2, cardID);

        pstmt.executeUpdate();
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
