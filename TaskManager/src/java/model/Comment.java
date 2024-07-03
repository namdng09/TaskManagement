package model;

import java.util.ArrayList;
import java.sql.*;
import dao.CommentDAO;
import utility.Utility;

public class Comment {

    private String commentID;
    private String firstName;
    private String lastName;
    private Timestamp createDate;
    private String comment;

    public Comment() {
    }

    public Comment(String commentID, Timestamp createDate, String comment) {
        this.commentID = commentID;
        this.createDate = createDate;
        this.comment = comment;
    }

    public Comment(String commentID, String firstName, String lastName, Timestamp createDate, String comment) {
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

    public Timestamp getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Timestamp createDate) {
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
            Timestamp date = rs.getTimestamp("CreatedDate");
            String textComment = rs.getString("Comment");

            Comment commentObj = new Comment(id, fName, lName, date, textComment);
            comments.add(commentObj);
        } catch (SQLException e) {
            //TODO: handle exception
            System.out.println("ERROR: " + e.getMessage());
        }
        return comments;
    }

    public void createComment(String cardID, String user_UID, String text) {
        CommentDAO commentDAO = new CommentDAO();
        Utility utils = new Utility();

        try {
            String prefix = "CO";
            ResultSet latestIDResultSet = commentDAO.getLastestCommentID();
            String id = utils.generateID(prefix, latestIDResultSet);
            Timestamp currentDate = utils.getCurrentDate();

            Comment comment = new Comment(id, currentDate, text);
            commentDAO.insertComment(comment, cardID, user_UID);
        } catch (SQLException e) {
            //TODO: handle exception
            System.out.println("ERROR: " + e.getMessage());
        }
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
