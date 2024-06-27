package model;

import java.sql.*;
import java.util.ArrayList;
import dao.ListTaskDAO;

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

    public ArrayList<ListTask> getAllListTaskByBroadID(String broadID) {
        ListTaskDAO listTaskDAO = new ListTaskDAO();
        ArrayList<ListTask> listTask = new ArrayList<>();
        try {
            ResultSet rs = listTaskDAO.getAllListTaskByBroadID(broadID);
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

    public void createListTask(String listTaskID, String broadID, String name) {
        ListTaskDAO listTaskDAO = new ListTaskDAO();
        try {
            ListTask listTask = new ListTask(listTaskID, name);
            listTaskDAO.insertListTask(listTask, broadID);
        } catch (SQLException e) {
            //TODO: handle exception
            System.out.println("ERROR: " + e.getMessage());
        }
    }

    public String generateListTaskID() {
        ListTaskDAO listTaskDAO = new ListTaskDAO();
        String lastID = null;
        try {
            ResultSet rs = listTaskDAO.getLastestListTaskID();
            if (rs.next()) {
                lastID = rs.getString("listTaskID");
            }

            if (lastID == null) {
                lastID = "LT0001";
            } else {
                int idNum = Integer.parseInt(lastID.substring(2));
                idNum++;
                lastID = String.format("LT%04d", idNum);
            }
        } catch (SQLException e) {
            //TODO: handle exception
            System.out.println("ERROR: " + e.getMessage());
        }
        return lastID;
    }

}
