package model;

import java.util.ArrayList;
import java.sql.*;
import dao.CheckListDAO;

public class CheckList {

    private String checkListID;
    private String checkListName;
    private boolean isChecked;

    public CheckList() {
    }

    public CheckList(String checkListID, String checkListName, boolean isChecked) {
        this.checkListID = checkListID;
        this.checkListName = checkListName;
        this.isChecked = isChecked;
    }

    public String getCheckListID() {
        return checkListID;
    }

    public void setCheckListID(String checkListID) {
        this.checkListID = checkListID;
    }

    public String getCheckListName() {
        return checkListName;
    }

    public void setCheckListName(String checkListName) {
        this.checkListName = checkListName;
    }

    public boolean isIsChecked() {
        return isChecked;
    }

    public void setIsChecked(boolean isChecked) {
        this.isChecked = isChecked;
    }

    @Override
    public String toString() {
        return "CheckList{" + "checkListID=" + checkListID + ", checkListName=" + checkListName + ", isChecked=" + isChecked + '}';
    }

    public ArrayList<CheckList> getAllCheckListByCardID(String cardID) {
        CheckListDAO checkListDAO = new CheckListDAO();
        ArrayList<CheckList> checkLists = new ArrayList<>();
        try {
            ResultSet rs = checkListDAO.getAllCheckListByCardID(cardID);
            String id = rs.getString("CheckListID");
            String name = rs.getString("Name");
            boolean checked = rs.getBoolean("isChecked");

            CheckList checkList = new CheckList(id, name, checked);
            checkLists.add(checkList);
        } catch (SQLException e) {
            //TODO: handle exception
            System.out.println("ERROR: " + e.getMessage());
        }
        return checkLists;
    }

    public void editCheckList(String checkListID, String name) {
        CheckListDAO checkListDAO = new CheckListDAO();
        try {
            checkListDAO.updateNameCheckList(checkListID, name);
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

    public String generateCheckListID() {
        CheckListDAO checkListDAO = new CheckListDAO();
        String lastID = null;
        try {
            ResultSet rs = checkListDAO.getLastestCheckListID();
            if (rs.next()) {
                lastID = rs.getString("CheckListID");
            }

            if (lastID == null) {
                lastID = "CL0001";
            } else {
                int idNum = Integer.parseInt(lastID.substring(2));
                idNum++;
                lastID = String.format("CL%04d", idNum);
            }
        } catch (SQLException e) {
            //TODO: handle exception
            System.out.println("ERROR: " + e.getMessage());
        }
        return lastID;
    }
}
