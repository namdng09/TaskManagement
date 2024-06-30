package model;

import java.util.ArrayList;
import java.util.Date;
import java.sql.*;
import dao.CommentDAO;

public class Comment {

    private String commentID;
    private String firstName;
    private String lastName;
    private Date createDate;
    private String comment;

    public Comment() {
    }

    public Comment(String commentID, String firstName, String lastName, Date createDate, String comment) {
        this.commentID = commentID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.createDate = createDate;
        this.comment = comment;
    }

    public String getCommentID() {
        return commentID;
    }

    public void setCommentID(String commentID) {
        this.commentID = commentID;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    
    

    @Override
    public String toString() {
        return "Comment{" + "commentID=" + commentID + ", createDate=" + createDate + ", comment=" + comment + '}';
    }

    public ArrayList<Comment> getAllCommentByCardID(String cardID) {
        CommentDAO commentDAO = new CommentDAO();
        ArrayList<Comment> comments = new ArrayList<>();
        try {
            ResultSet rs = commentDAO.getAllCommentByCardID(cardID);
            String id = rs.getString("CommentID");
            String fName = rs.getString("FirstName");
            String lName = rs.getString("LastName");
            Date date = rs.getDate("CreatedDate");
            String textComment = rs.getString("Comment");

            Comment commentObj = new Comment(id, fName, lName, date, textComment);
            comments.add(commentObj);
        } catch (SQLException e) {
            //TODO: handle exception
            System.out.println("ERROR: " + e.getMessage());
        }
        return comments;
    }

    public String generateCommentID() {
        CommentDAO commentDAO = new CommentDAO();
        String lastID = null;
        try {
            ResultSet rs = commentDAO.getLastestCommentID();
            if (rs.next()) {
                lastID = rs.getString("CommentID");
            }

            if (lastID == null) {
                lastID = "CO0001";
            } else {
                int idNum = Integer.parseInt(lastID.substring(2));
                idNum++;
                lastID = String.format("CO%04d", idNum);
            }
        } catch (SQLException e) {
            //TODO: handle exception
            System.out.println("ERROR: " + e.getMessage());
        }
        return lastID;
    }

    public void editComment(String commentID, String comment) {
        CommentDAO commentDAO = new CommentDAO();
        try {
            commentDAO.updateComment(commentID, comment);
        } catch (SQLException e) {
            //TODO: handle exception
            System.out.println("ERROR: " + e.getMessage());
        }
    }

    public void removeComment(String commentID) {
        CommentDAO commentDAO = new CommentDAO();
        try {
            commentDAO.deleteComment(commentID);
        } catch (SQLException e) {
            //TODO: handle exception
            System.out.println("ERROR: " + e.getMessage());
        }
    }

}
