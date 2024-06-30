package model;

import java.sql.*;
import java.util.ArrayList;
import dao.CardDAO;
import utility.Utility;

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
        Utility utils = new Utility();
        try {
            String prefix = "CA";
            ResultSet latestIDResultSet = cardDAO.getLastestCardID();
            String id = utils.generateID(prefix, latestIDResultSet);
            Date currentDate = utils.getCurrentDate();
            Card card = new Card(id, name, currentDate);

            cardDAO.insertCard(card, listTaskID);
        } catch (SQLException e) {
            //TODO: handle exception
            System.out.println("ERROR: " + e.getMessage());
        }
    }

    public void removeCard(String cardID) {
        CardDAO cardDAO = new CardDAO();
        try {
            cardDAO.deleteCard(cardID);
        } catch (SQLException e) {
            //TODO: handle exception
            System.out.println("ERROR: " + e.getMessage());
        }
    }

    public void editCardName(String cardID, String name) {
        CardDAO cardDAO = new CardDAO();
        try {
            cardDAO.updateCardName(cardID, name);
        } catch (SQLException e) {
            //TODO: handle exception
            System.out.println("ERROR: " + e.getMessage());
        }
    }

    public void editCardDescription(String cardID, String description) {
        CardDAO cardDAO = new CardDAO();
        try {
            cardDAO.updateCardDescription(cardID, description);
        } catch (SQLException e) {
            //TODO: handle exception
            System.out.println("ERROR: " + e.getMessage());
        }
    }

    public void editCardDueDate(String cardID, Date dueDate) {
        CardDAO cardDAO = new CardDAO();
        try {
            cardDAO.updateCardDueDate(cardID, dueDate);
        } catch (SQLException e) {
            //TODO: handle exception
            System.out.println("ERROR: " + e.getMessage());
        }
    }
}
