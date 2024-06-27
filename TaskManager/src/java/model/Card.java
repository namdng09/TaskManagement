package model;

import java.sql.*;
import java.util.ArrayList;
import dao.CardDAO;
import java.time.LocalDate;

public class Card {

    private String cardID;
    private String cardName;
    private String description;
    private Date createdDate;
    private Date dueDate;
    private ArrayList<Comment> comments;
    private ArrayList<CheckList> checkLists;

    public Card() {
    }

    public Card(String cardID, String cardName, Date createdDate) {
        this.cardID = cardID;
        this.cardName = cardName;
        this.createdDate = createdDate;
    }

    public Card(String cardID, String cardName, String description, Date createdDate, Date dueDate, ArrayList<Comment> comments, ArrayList<CheckList> checkLists) {
        this.cardID = cardID;
        this.cardName = cardName;
        this.description = description;
        this.createdDate = createdDate;
        this.dueDate = dueDate;
        this.comments = comments;
        this.checkLists = checkLists;
    }

    public String getCardID() {
        return cardID;
    }

    public void setCardID(String cardID) {
        this.cardID = cardID;
    }

    public String getCardName() {
        return cardName;
    }

    public void setCardName(String cardName) {
        this.cardName = cardName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    public ArrayList<Comment> getComments() {
        return comments;
    }

    public void setComments(ArrayList<Comment> comments) {
        this.comments = comments;
    }

    public ArrayList<CheckList> getCheckLists() {
        return checkLists;
    }

    public void setCheckLists(ArrayList<CheckList> checkLists) {
        this.checkLists = checkLists;
    }

    @Override
    public String toString() {
        return "Card{" + "cardID=" + cardID + ", cardName=" + cardName + ", description=" + description + ", createdDate=" + createdDate + ", dueDate=" + dueDate + ", comments=" + comments + ", checkLists=" + checkLists + '}';
    }

    public ArrayList<Card> getAllCardByListTaskID(String listTaskID) {
        CardDAO cardDAO = new CardDAO();
        ArrayList<Card> cards = new ArrayList<>();
        try {
            ResultSet rs = cardDAO.getAllCardByListTaskID(listTaskID);
            while (rs.next()) {
                String id = rs.getString("CardID");
                String name = rs.getString("Name");
                String des = rs.getString("Description");
                Date cDate = rs.getDate("CreatedDate");
                Date dDate = rs.getDate("DueDate");
                ArrayList<Comment> listComments = (new Comment()).getAllCommentByCardID(id);
                ArrayList<CheckList> allCheckLists = (new CheckList()).getAllCheckListByCardID(id);

                Card card = new Card(id, name, des, cDate, dDate, listComments, allCheckLists);
                cards.add(card);
            }
        } catch (SQLException e) {
            //TODO: handle exception
            System.out.println("ERROR: " + e.getMessage());
        }
        return cards;
    }

    public void createCard(String listTaskID, String name) {
        CardDAO cardDAO = new CardDAO();
        String id = this.generateCardID();
        Date currentDate = this.getCurrentDate();
        Card card = new Card(id, name, currentDate);
        try {
            cardDAO.insertCard(card, listTaskID);
        } catch (SQLException e) {
            //TODO: handle exception
            System.out.println("ERROR: " + e.getMessage());
        }
    }

    public String generateCardID() {
        CardDAO cardDAO = new CardDAO();
        String lastID = null;
        try {
            ResultSet rs = cardDAO.getLastestCardID();
            if (rs.next()) {
                lastID = rs.getString("CardID");
            }

            if (lastID == null) {
                lastID = "CA0001";
            } else {
                int idNum = Integer.parseInt(lastID.substring(2));
                idNum++;
                lastID = String.format("CA%04d", idNum);
            }
        } catch (SQLException e) {
            //TODO: handle exception
            System.out.println("ERROR: " + e.getMessage());
        }
        return lastID;
    }

    public Date getCurrentDate() {
        LocalDate currentDate = LocalDate.now(); // Get current date
        Date sqlDate = Date.valueOf(currentDate); // Convert to java.sql.Date
        return sqlDate;
    }
}
