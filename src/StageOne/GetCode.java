package StageOne;

import javax.swing.*;
import java.awt.*;
import java.sql.*;

public class GetCode {

    public static Connection dbConnector() {
        try {
            Class.forName("org.sqlite.JDBC");
            Connection conn = DriverManager.getConnection("jdbc:sqlite:Database/Moodify.db");
            return conn;

        } catch (Exception e) {
            return null;
        }
    }

    public static String GCode(){
        Connection conn = null;
        String query = "SELECT * from Uinfo";
        String Code = "";
        try {
            conn = dbConnector();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                Code = rs.getString(2);
            }
            return Code;
        }
        catch (SQLException sqle) {
            sqle.printStackTrace();
        }
        return GCode();
    }
    public static String RT(){
        Connection conn = null;
        String query = "SELECT * from Uinfo";
        String Code = "";
        try {
            conn = dbConnector();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                Code = rs.getString(3);
            }
            return Code;
        }
        catch (SQLException sqle) {
            sqle.printStackTrace();
        }
        return RT();
    }


    public static void main(String[] args) {
        GCode();
        RT();
    }
}


