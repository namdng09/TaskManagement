package model;

import java.sql.*;
import java.util.ArrayList;
import java.time.LocalDate;
import dao.BroadDAO;
import utility.Utility;

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

    public Broad(String broadID, String broadName, Date createDate, boolean isPublic, String image) {
        this.broadID = broadID;
        this.broadName = broadName;
        this.createDate = createDate;
        this.isPublic = isPublic;
        this.image = image;
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

    public ArrayList<Broad> getAllBroadByUserUID(String useruid) {
        BroadDAO broadDAO = new BroadDAO();
        ArrayList<Broad> broads = new ArrayList<>();
        try {
            ResultSet rs = broadDAO.getAllBroadByUserUID(useruid);
            while (rs.next()) {
                String id = rs.getString("BroadID");
                String name = rs.getString("Name");
                Date date = rs.getDate("CreateDate");
                boolean accessControl = rs.getBoolean("isPublic");
                String img = rs.getString("image");
                ArrayList<ListTask> listTask = (new ListTask()).getAllListTaskByBroadID(id);

                Broad broad = new Broad(id, name, date, accessControl, img, listTask);
                broads.add(broad);
            }
        } catch (SQLException e) {
            //TODO: handle exception
            System.out.println("ERROR: " + e.getMessage());
        }
        return broads;
    }

    public ArrayList<Broad> getIDNameBroadByUserUID(String useruid) {
        BroadDAO broadDAO = new BroadDAO();
        ArrayList<Broad> listBroads = new ArrayList<>();
        Broad broad = new Broad();
        try {
            ResultSet rs = broadDAO.getAllBroadByUserUID(useruid);
            while (rs.next()) {
                String id = rs.getString("BroadID");
                String name = rs.getString("Name");
                broad.setBroadID(id);
                broad.setBroadName(name);
                listBroads.add(broad);
            }
        } catch (SQLException e) {
            //TODO: handle exception
            System.out.println("ERROR: " + e.getMessage());
        }
        return listBroads;
    }

    public void createBroad(String useruid, String name, boolean accessControl, String image) {
        Utility utils = new Utility();
        BroadDAO broadDAO = new BroadDAO();
        try {
            String prefix = "BR";
            ResultSet latestIDResultSet = broadDAO.getLastestBroadID();
            String id = utils.generateID(prefix, latestIDResultSet);
            Date currentDate = utils.getCurrentDate();
            Broad broad = new Broad(id, name, currentDate, accessControl, image);
            broadDAO.insertBroad(broad, useruid);
        } catch (SQLException e) {
            //TODO: handle exception
            System.out.println("ERROR: " + e.getMessage());
        }
    }

    public String generateBroadID() {
        BroadDAO broadDAO = new BroadDAO();
        String lastID = null;
        try {
            ResultSet rs = broadDAO.getLastestBroadID();
            if (rs.next()) {
                lastID = rs.getString("BroadID");
            }

            if (lastID == null) {
                lastID = "BR0001";
            } else {
                int idNum = Integer.parseInt(lastID.substring(2));
                idNum++;
                lastID = String.format("BR%04d", idNum);
            }
        } catch (SQLException e) {
            //TODO: handle exception
            System.out.println("ERROR: " + e.getMessage());
        }
        return lastID;
    }

    public void changeAccessControl(String broadID) {
        BroadDAO broadDAO = new BroadDAO();
        try {
            broadDAO.toggleIsPublic(broadID);
        } catch (SQLException e) {
            //TODO: handle exception
            System.out.println("ERROR: " + e.getMessage());
        }
    }

    public void changeNameOfBroad(String broadID, String newName) {
        BroadDAO broadDAO = new BroadDAO();
        try {
            broadDAO.renameBroad(broadID, newName);
        } catch (SQLException e) {
            //TODO: handle exception
            System.out.println("ERROR: " + e.getMessage());
        }
    }
}
