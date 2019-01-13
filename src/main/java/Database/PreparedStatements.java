package Database;

import Models.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PreparedStatements implements IPreparedStatements {

    static PreparedStatement PrepareStat = null;
   static MysqlCon con = new MysqlCon();
    static Connection Conn;

    @Override
    public List<User> getAllUsers() {

        List<User> getUsers = new ArrayList<>();
                try {

            Conn = con.getConnection();

            String getQueryStatement = "SELECT * FROM player";

            PrepareStat = Conn.prepareStatement(getQueryStatement);

            // Execute the Query, and get a java ResultSet
            ResultSet rs = PrepareStat.executeQuery();

            // Let's iterate through the java ResultSet
            while (rs.next()) {
                String name = rs.getString("uname");
                String pword = rs.getString("pword");
                int id = rs.getInt("ID");

                getUsers.add(new User(id, name, pword));



            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

                return getUsers;
    }


    @Override
    public User getUserByID(int id) {
        Conn = con.getConnection();
        User user = new User();
        try {

            Conn = con.getConnection();

            String getQueryStatement = "SELECT * FROM player WHERE ID = ?";

            PrepareStat = Conn.prepareStatement(getQueryStatement);
            PrepareStat.setObject(1, id);

            // Execute the Query, and get a java ResultSet
            ResultSet rs = PrepareStat.executeQuery();

            // Let's iterate through the java ResultSet
            while (rs.next()) {
                String name = rs.getString("uname");
                String pword = rs.getString("pword");
                user = new User(id, name, pword);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
            return user;


    }



    @Override
    public void addUser(String username, String password) {
        try {
            String insertQueryStatement = "INSERT  INTO  player (uname, pword)  VALUES  (?,?)";

            PrepareStat = Conn.prepareStatement(insertQueryStatement);
            PrepareStat.setString(1, username);
            PrepareStat.setString(2, password);

            // execute insert SQL statement
            PrepareStat.executeUpdate();

        } catch (

                SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean CheckUser(String username, String password) {
        User user = new User();
        List<User> userList = new ArrayList<>();
        boolean playerExists = false;

        try {
            String getQueryStatement = "SELECT * FROM player WHERE uname = ? AND pword = ?";
            PrepareStat = Conn.prepareStatement(getQueryStatement);
            PrepareStat.setString(1, username);
            PrepareStat.setString(2, password);

            ResultSet rs = PrepareStat.executeQuery();

            // Let's iterate through the java ResultSet
            while (rs.next()) {
                String name = rs.getString("uname");
                String pword = rs.getString("pword");
                user = new User(name, pword);
                userList.add(user);
            }

            if(userList.size() == 1) {
                playerExists = true;
            }

            if(userList.size() < 1) {
                playerExists = false;
            }

            if(userList.size() > 1) {
                playerExists = true;
                System.out.println("there were more players with that name.");
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }
        return playerExists;
    }

    @Override
    public void getPointsByUser(User user) {
        // nothing yet

    }


}
