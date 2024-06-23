package model;

import java.util.ArrayList;
import java.util.Date;
import java.sql.*;

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
    
    
}
