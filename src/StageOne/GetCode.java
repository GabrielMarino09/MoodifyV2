//GetCode a part of the package named StageOne.
package StageOne;

//Imports the necessary libraries to execute this class.
import java.sql.*;

public class GetCode {

    // Establishes the connection with the Moodify Database.
    public static Connection dbConnector() {
        try {
            Class.forName("org.sqlite.JDBC");
            Connection conn = DriverManager.getConnection("jdbc:sqlite:Database/Moodify.db");
            return conn;

        } catch (Exception e) {
            return null;
        }
    }

    // Method to get the Code from the database. This is used by other classes.
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

    // Method to get the Refresh Token from the database. This is used by other classes.
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
        // When executed, it first it gets the Access Code
        GCode();

        // Then, it gets the Refresh Token
        RT();
    }
}


