package Database;

import java.sql.*;

class MysqlCon{

    static Connection Conn = null;
    static PreparedStatement PrepareStat = null;

    public Connection getConnection() {
        makeJDBCConnection();
        return Conn;
    }

    public MysqlCon() {

    }



    private static void makeJDBCConnection() {

        try {
            Class.forName("com.mysql.jdbc.Driver");
            System.out.println("JDBC Driver registered.");
        } catch (ClassNotFoundException e) {
            System.out.println("Could not find JDBC driver.");
            e.printStackTrace();
            return;
        }

        try {
            // DriverManager: The basic service for managing a set of JDBC drivers.
            Conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/ana_virtually", "root", "root");
            if (Conn != null) {
                System.out.println("Connection successfull.");
            } else {
                System.out.println("Failed Connection.");
            }
        } catch (SQLException e) {
            System.out.println("Failed connection.");
            e.printStackTrace();
            return;
        }
    }

}
