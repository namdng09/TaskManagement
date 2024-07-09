package model;

import java.sql.*;
import java.util.ArrayList;
import dao.ListTaskDAO;
import utility.Utility;

public class ListTask {

    private String listTaskID;
    private String listTaskName;
    private ArrayList<Card> cards;

    public ListTask() {
    }

    public ListTask(String listTaskID, String listTaskName) {
        this.listTaskID = listTaskID;
        this.listTaskName = listTaskName;
    }

    public ListTask(String listTaskID, String listTaskName, ArrayList<Card> cards) {
        this.listTaskID = listTaskID;
        this.listTaskName = listTaskName;
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

    public ArrayList<Card> getCards() {
        return cards;
    }

    public void setCards(ArrayList<Card> cards) {
        this.cards = cards;
    }

    @Override
    public String toString() {
        return "ListTask{" + "listTaskID=" + listTaskID + ", listTaskName=" + listTaskName + ", cards=" + cards + '}';
    }

    public ArrayList<ListTask> getAllListTaskByBoardID(String boardID) {
        ListTaskDAO listTaskDAO = new ListTaskDAO();
        ArrayList<ListTask> listTask = new ArrayList<>();
        try {
            ResultSet rs = listTaskDAO.getAllListTaskByBoardID(boardID);
            while (rs.next()) {
                String id = rs.getString("ListTaskID");
                String name = rs.getString("Name");
                ArrayList<Card> cards = (new Card()).getAllCardByListTaskID(id);

                ListTask task = new ListTask(id, name, cards);
                listTask.add(task);
            }
        } catch (SQLException e) {
            //TODO: handle exception
            System.out.println("ERROR: " + e.getMessage());
        }
        return listTask;
    }

    public void createListTask(String boardID, String name) {
        ListTaskDAO listTaskDAO = new ListTaskDAO();
        Utility utils = new Utility();
        try {
            String prefix = "LT";
            ResultSet latestIDResultSet = listTaskDAO.getLastestListTaskID();
            String id = utils.generateID(prefix, latestIDResultSet);
            ListTask listTask = new ListTask(id, name);

            listTaskDAO.insertListTask(listTask, boardID);
        } catch (SQLException e) {
            //TODO: handle exception
            System.out.println("ERROR: " + e.getMessage());
        }
    }
    
    public void removeListTask(String listTaskID) {
        ListTaskDAO listTaskDAO = new ListTaskDAO();
        try {
            listTaskDAO.deleteListTask(listTaskID);
        } catch (SQLException e) {
            //TODO: handle exception
            System.out.println("ERROR: " + e.getMessage());
        }
    }

    public void renameListTask(String listTaskID, String name) {
        ListTaskDAO listTaskDAO = new ListTaskDAO();
        try {
            listTaskDAO.renameListTask(listTaskID, name);
        } catch (SQLException e) {
            //TODO: handle exception
            System.out.println("ERROR: " + e.getMessage());
        }
    }
}
