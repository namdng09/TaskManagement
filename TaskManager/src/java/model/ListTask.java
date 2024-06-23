package model;

import java.sql.*;
import java.util.ArrayList;

public class ListTask {
    private String listTaskID;
    private String listTaskName;
    private String position;
    private ArrayList<Card> cards;

    public ListTask() {
    }

    public ListTask(String listTaskID, String listTaskName, String position, ArrayList<Card> cards) {
        this.listTaskID = listTaskID;
        this.listTaskName = listTaskName;
        this.position = position;
        this.cards = cards;
    }

    public String getListTaskID() {
        return listTaskID;
    }

    public void setListTaskID(String listTaskID) {
        this.listTaskID = listTaskID;
    }

    public String getListTaskName() {
        return listTaskName;
    }

    public void setListTaskName(String listTaskName) {
        this.listTaskName = listTaskName;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public ArrayList<Card> getCards() {
        return cards;
    }

    public void setCards(ArrayList<Card> cards) {
        this.cards = cards;
    }

    @Override
    public String toString() {
        return "ListTask{" + "listTaskID=" + listTaskID + ", listTaskName=" + listTaskName + ", position=" + position + ", cards=" + cards + '}';
    }
    
    
}
