package model;

import java.util.ArrayList;
import java.util.Date;
import java.sql.*;

public class Broad {
    private String broadID;
    private String broadName;
    private Date createDate;
    private boolean isPublic;
    private String image;
    private ArrayList<ListTask> listOfTask;

    public Broad() {
    }

    public Broad(String broadID, String broadName, Date createDate, boolean isPublic, String image, ArrayList<ListTask> listOfTask) {
        this.broadID = broadID;
        this.broadName = broadName;
        this.createDate = createDate;
        this.isPublic = isPublic;
        this.image = image;
        this.listOfTask = listOfTask;
    }

    public String getBroadID() {
        return broadID;
    }

    public void setBroadID(String broadID) {
        this.broadID = broadID;
    }

    public String getBroadName() {
        return broadName;
    }

    public void setBroadName(String broadName) {
        this.broadName = broadName;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public boolean isIsPublic() {
        return isPublic;
    }

    public void setIsPublic(boolean isPublic) {
        this.isPublic = isPublic;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public ArrayList<ListTask> getListOfTask() {
        return listOfTask;
    }

    public void setListOfTask(ArrayList<ListTask> listOfTask) {
        this.listOfTask = listOfTask;
    }

    @Override
    public String toString() {
        return "Broad{" + "broadID=" + broadID + ", broadName=" + broadName + ", createDate=" + createDate + ", isPublic=" + isPublic + ", image=" + image + ", listOfTask=" + listOfTask + '}';
    }

    
}
