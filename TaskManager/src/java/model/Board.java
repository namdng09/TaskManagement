package model;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import dao.BoardDAO;
import dao.UserDAO;
import utility.Utility;

public class Board {

    private String boardID;
    private String boardName;
    private String owner;
    private Timestamp createDate;
    private boolean publiced;
    private boolean completed;
    private String image;
    private String description;
    private ArrayList<ListTask> listOfTask;

    public Board() {
    }

    public Board(String boardID, String boardName, String owner, Timestamp createDate, boolean publiced, boolean completed, String image, String description, ArrayList<ListTask> listOfTask) {
        this.boardID = boardID;
        this.boardName = boardName;
        this.owner = owner;
        this.createDate = createDate;
        this.publiced = publiced;
        this.completed = completed;
        this.image = image;
        this.description = description;
        this.listOfTask = listOfTask;
    }

    public Board(String boardID, String boardName, String owner, Timestamp createDate, boolean publiced, boolean completed, String image, String description) {
        this.boardID = boardID;
        this.boardName = boardName;
        this.owner = owner;
        this.createDate = createDate;
        this.publiced = publiced;
        this.completed = completed;
        this.image = image;
        this.description = description;
    }

    public Board(String boardID, String boardName, Timestamp createDate, boolean publiced, boolean completed, String image, String description) {
        this.boardID = boardID;
        this.boardName = boardName;
        this.createDate = createDate;
        this.publiced = publiced;
        this.completed = completed;
        this.image = image;
        this.description = description;
    }

    public Board(String id, String name, boolean permissions, boolean status, String backgroundImage, String description) {
        this.boardID = id;
        this.boardName = name;
        this.publiced = permissions;
        this.completed = status;
        this.image = backgroundImage;
        this.description = description;
    }

    // Getter and Setter methods
    public String getBoardID() {
        return boardID;
    }

    public void setBoardID(String boardID) {
        this.boardID = boardID;
    }

    public String getBoardName() {
        return boardName;
    }

    public void setBoardName(String boardName) {
        this.boardName = boardName;
    }

    public Timestamp getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Timestamp createDate) {
        this.createDate = createDate;
    }

    public boolean isPubliced() {
        return publiced;
    }

    public void setPubliced(boolean publiced) {
        this.publiced = publiced;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public Board getBoardByBoardID(String boardID) {
        BoardDAO boardDAO = new BoardDAO();
        Board board = new Board();
        try {
            ResultSet rs = boardDAO.getBoardByBoardID(boardID);
            if (rs.next()) {
                String id = rs.getString("BoardID");
                String name = rs.getString("Name");
                Timestamp date = rs.getTimestamp("CreatedDate");
                boolean accessControl = rs.getBoolean("isPublic");
                boolean completed = rs.getBoolean("isCompleted");
                String des = rs.getString("Description");
                String img = rs.getString("Image");
                ArrayList<ListTask> listTask = (new ListTask()).getAllListTaskByBoardID(id);

                board = new Board(id, name, owner, date, accessControl, completed, img, des, listTask);
            }
        } catch (SQLException e) {
            // Handle exception
            System.out.println("ERROR: " + e.getMessage());
        }
        return board;
    }

    public ArrayList<Board> getAllBoardByUserUID(String useruid) {
        BoardDAO boardDAO = new BoardDAO();
        ArrayList<Board> listBoards = new ArrayList<>();
        try {
            ResultSet rs = boardDAO.getAllBoardByUserUID(useruid);
            while (rs.next()) {
                String id = rs.getString("BoardID");
                String name = rs.getString("Name");
                String owner = rs.getString("FullName");
                Timestamp date = rs.getTimestamp("CreatedDate");
                boolean publiced = rs.getBoolean("isPublic");
                boolean completed = rs.getBoolean("isCompleted");
                String description = rs.getString("Description");
                String img = rs.getString("Image");

                Board board = new Board(id, name, owner, date, publiced, completed, img, description);
                listBoards.add(board);
            }
        } catch (SQLException e) {
            // Handle exception
            System.out.println("ERROR: " + e.getMessage());
        }
        return listBoards;
    }

    public ArrayList<Board> searchBoardsByName(String useruid, String partOfName) {
        BoardDAO boardDAO = new BoardDAO();
        ArrayList<Board> boards = new ArrayList<>();
        try {
            ResultSet rs = boardDAO.getAllBoardByName(useruid, partOfName);
            while (rs.next()) {
                String id = rs.getString("BoardID");
                String name = rs.getString("Name");
                String owner = rs.getString("FullName");
                Timestamp date = rs.getTimestamp("CreatedDate");
                boolean publiced = rs.getBoolean("isPublic");
                boolean completed = rs.getBoolean("isCompleted");
                String description = rs.getString("Description");
                String img = rs.getString("Image");

                Board board = new Board(id, name, owner, date, publiced, completed, img, description);
                boards.add(board);
            }
        } catch (SQLException e) {
            // Handle exception
            System.out.println("ERROR: " + e.getMessage());
        }
        return boards;
    }

    public void createBoard(String useruid, String name, boolean accessControl, String description, String image) {
        Utility utils = new Utility();
        BoardDAO boardDAO = new BoardDAO();
        try {
            String prefix = "BR";
            ResultSet latestIDResultSet = boardDAO.getLastestBoardID();
            String id = utils.generateID(prefix, latestIDResultSet);
            Timestamp currentDate = utils.getCurrentDate();
            boolean completed = false;
            Board board = new Board(id, name, currentDate, accessControl, completed, image, description);
            boardDAO.insertBoard(board, useruid);
            boardDAO.insertBoardMember(useruid, id);
        } catch (SQLException e) {
            // Handle exception
            System.out.println("ERROR: " + e.getMessage());
        }
    }

    public void changeAccessControl(String boardID, boolean publiced) {
        BoardDAO boardDAO = new BoardDAO();
        try {
            boardDAO.toggleIsPublic(boardID, publiced);
        } catch (SQLException e) {
            // Handle exception
            System.out.println("ERROR: " + e.getMessage());
        }
    }

    public void editBoard(Board board) {
        BoardDAO boardDAO = new BoardDAO();
        try {
            boardDAO.updateBoard(board);
        } catch (SQLException e) {
            // Handle exception
            System.out.println("ERROR: " + e.getMessage());
        }
    }

    public void editBoardName(String boardID, String newName) {
        BoardDAO boardDAO = new BoardDAO();
        try {
            boardDAO.updateBoardName(boardID, newName);
        } catch (SQLException e) {
            // Handle exception
            System.out.println("ERROR: " + e.getMessage());
        }
    }

    public ArrayList<User> getAllMembers(String boardID) {
        BoardDAO boardDAO = new BoardDAO();
        ArrayList<User> members = new ArrayList<>();
        try {
            ResultSet rs = boardDAO.getAllUserByBoardID(boardID);
            while (rs.next()) {
                String id = rs.getString("User_UID");
                String username = rs.getString("Username");
                String email = rs.getString("Email");
                String fName = rs.getString("FirstName");
                String lName = rs.getString("LastName");
                User user = new User(id, username, email, fName, lName);

                members.add(user);
            }
        } catch (SQLException e) {
            // Handle exception
            System.out.println("ERROR: " + e.getMessage());
        }
        return members;
    }

    public void addMember(String useruid, String boardID) {
        BoardDAO boardDAO = new BoardDAO();
        try {
            boardDAO.insertBoardMember(useruid, boardID);
        } catch (SQLException e) {
            // Handle exception
            System.out.println("ERROR: " + e.getMessage());
        }
    }

    public void removeMember(String useruid, String boardID) {
        BoardDAO boardDAO = new BoardDAO();
        try {
            boardDAO.deleteBoardMember(useruid, boardID);
        } catch (SQLException e) {
            // Handle exception
            System.out.println("ERROR: " + e.getMessage());
        }
    }

    public void addBookmark(String useruid, String boardID) {

    }

    public void removeBookmark(String useruid, String boardID) {

    }

    public ArrayList<User> searchUserNotInBoard(String boardID, String partOfName) {
        BoardDAO boardDAO = new BoardDAO();
        UserDAO userDAO = new UserDAO();
        ArrayList<User> listUserNotInBoard = new ArrayList<>();
        Set<String> boardMemberUserUID = new HashSet<>();
        try {
            ResultSet rsAllUsers = userDAO.getAllUserByPartOfname(partOfName);
            ResultSet rsBoardMembers = boardDAO.getAllUserByBoardID(boardID);

            while (rsBoardMembers.next()) {
                boardMemberUserUID.add(rsBoardMembers.getString("User_UID"));
            }

            while (rsAllUsers.next()) {
                String id = rsAllUsers.getString("User_UID");
                if (!boardMemberUserUID.contains(id)) {
                    String uName = rsAllUsers.getString("Username");
                    String email = rsAllUsers.getString("Email");
                    String fName = rsAllUsers.getString("FirstName");
                    String lName = rsAllUsers.getString("LastName");
                    User user = new User(id, uName, email, fName, lName);
                    listUserNotInBoard.add(user);
                }
            }
        } catch (SQLException e) {
            // Handle exception
            System.out.println("ERROR: " + e.getMessage());
        }
        return listUserNotInBoard;
    }

    public boolean isExistBoardName(String name) {
        BoardDAO boardDAO = new BoardDAO();
        boolean flag = false;
        try {
            ResultSet rs = boardDAO.getBoardByName(name);
            if (rs.next()) {
                flag = true;
            }
        } catch (SQLException e) {
            //TODO: handle exception
            System.out.println("ERROR " + e.getMessage());
        }
        return flag;
    }

    public void checkValidBoardName(String name) throws Exception {
        String message;
        if (this.isExistBoardName(name)) {
            message = "Board name is already existed!";
            throw new Exception(message);
        }
    }

    public void deleteBoard(String boardID) {
        BoardDAO boardDAO = new BoardDAO();
        try {
            boardDAO.deleteBoard(boardID);
        } catch (SQLException e) {
            //TODO: handle exception
            System.out.println("ERROR " + e.getMessage());
        }
    }
}
