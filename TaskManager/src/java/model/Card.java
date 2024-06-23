package model;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;

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
    
    
}
