package model;

import java.util.ArrayList;
import java.sql.*;
import dao.CheckListDAO;
import utility.Utility;

public class CheckList {

    private String checkListID;
    private String checkListTitle;
    private boolean checked;

    public CheckList() {
    }

    public CheckList(String checkListID, String checkListTitle, boolean checked) {
        this.checkListID = checkListID;
        this.checkListTitle = checkListTitle;
        this.checked = checked;
    }

    public String getCheckListID() {
        return checkListID;
    }

    public void setCheckListID(String checkListID) {
        this.checkListID = checkListID;
    }

    public String getCheckListTitle() {
        return checkListTitle;
    }

    public void setCheckListTitle(String checkListTitle) {
        this.checkListTitle = checkListTitle;
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }

    @Override
    public String toString() {
        return "CheckList{" + "checkListID=" + checkListID + ", checkListTitle=" + checkListTitle + ", checked=" + checked + '}';
    }

    public ArrayList<CheckList> getAllCheckListByCardID(String cardID) {
        CheckListDAO checkListDAO = new CheckListDAO();
        ArrayList<CheckList> checkLists = new ArrayList<>();
        try {
            ResultSet rs = checkListDAO.getAllCheckListByCardID(cardID);
            while (rs.next()) {
                String id = rs.getString("CheckListID");
                String tilte = rs.getString("Title");
                boolean isChecked = rs.getBoolean("isChecked");

                CheckList checkList = new CheckList(id, tilte, isChecked);
                checkLists.add(checkList);
            }
        } catch (SQLException e) {
            //TODO: handle exception
            System.out.println("ERROR: " + e.getMessage());
        }
        return checkLists;
    }

    public void createCheckList(String cardID, String checkListTitle) {
        Utility utils = new Utility();
        CheckListDAO checkListDAO = new CheckListDAO();
        try {
            String prefix = "CL";
            ResultSet latestIDResultSet = checkListDAO.getLastestCheckListID();
            String id = utils.generateID(prefix, latestIDResultSet);
            boolean checked = false;

            CheckList checkList = new CheckList(id, checkListTitle, checked);
            checkListDAO.insertCheckList(checkList, cardID);
        } catch (SQLException e) {
            //TODO: handle exception
            System.out.println("ERROR: " + e.getMessage());
        }
    }

    public void editCheckListTitle(String checkListID, String tilte) {
        CheckListDAO checkListDAO = new CheckListDAO();
        try {
            checkListDAO.updateCheckListTitle(checkListID, tilte);
        } catch (SQLException e) {
            //TODO: handle exception
            System.out.println("ERROR: " + e.getMessage());
        }
    }

    public void editCheckListChecked(String checkListID, boolean checked) {
        CheckListDAO checkListDAO = new CheckListDAO();
        try {
            checkListDAO.updateCheckListChecked(checkListID, checked);
        } catch (SQLException e) {
            //TODO: handle exception
            System.out.println("ERROR: " + e.getMessage());
        }
    }

    public void removeCheckList(String checkListID) {
        CheckListDAO checkListDAO = new CheckListDAO();
        try {
            checkListDAO.deleteCheckList(checkListID);
        } catch (SQLException e) {
            //TODO: handle exception
            System.out.println("ERROR: " + e.getMessage());
        }
    }

}
