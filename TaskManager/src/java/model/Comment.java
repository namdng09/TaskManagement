package model;

import java.util.ArrayList;
import java.util.Date;
import java.sql.*;

public class Comment {
    private String commentID;
    private Date createDate;
    private String comment;

    public Comment() {
    }

    public Comment(String commentID, Date createDate, String comment) {
        this.commentID = commentID;
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

    @Override
    public String toString() {
        return "Comment{" + "commentID=" + commentID + ", createDate=" + createDate + ", comment=" + comment + '}';
    }
    
    
}
