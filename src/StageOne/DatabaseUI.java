//DatabaseUI is a part of the package named StageOne.
package StageOne;


//Imports all the necessary libraries to execute this class.
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import java.sql.*;
import java.util.ArrayList;

public class DatabaseUI extends JDialog {
    //Establishes the connection between the form for the DatabaseUI and its contents, so things can be altered, and actions can be executed.
    JPanel DatabaseUIPanel;
    private JPanel CompanyPanel;
    private JLabel MoodifyLogoLabel;
    private JPanel ListNamePanel;
    private JPanel HeadingsPanel;
    private JButton BackButton;
    private JLabel ArtistHeadingLabel;
    private JLabel AlbumHeadingLabel;
    private JLabel SongHeadingLabel;
    private JLabel MoodHeadingLabel;
    private JTable Table01;
    private JScrollPane sp;

    //Establishes the connection with the Moodify database.
    public Connection dbConnector() {
        try {
            Class.forName("org.sqlite.JDBC");
            Connection conn = DriverManager.getConnection("jdbc:sqlite:Database/Moodify.db");
            return conn;
        }
        catch (Exception e) {
            return null;
        }
    }

    // Retrieves the information for trackinfo from the database.
    private String[][] getData() {
        ArrayList<String[]> table = new ArrayList<>();
        Connection conn = null;
        String query = "SELECT * from TrackInfo";
        int NUM_COLS = 4;
        try {
            conn = dbConnector();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            // Loops through the database. If it has something on the next line, then do the following.
            while (rs.next()) {
                // Loops through the database. If it has something on the next line, then do the following.
                String[] dataRow = new String[NUM_COLS];
                dataRow[0] = rs.getString(10);
                dataRow[1] = rs.getString(9);
                dataRow[2] = rs.getString(1);
                dataRow[3] = rs.getString(8);
                // Adds the information collected to the table arraylist.
                table.add(dataRow);
            }

            // Builds the full table based on the information previously collected.
            String[][] fullTable = new String[table.size()][NUM_COLS];
            for (int i = 0; i < table.size(); i++) {
                fullTable[i] = table.get(i);
            }
            return fullTable;

        }
        catch (SQLException sqle) {
            sqle.printStackTrace();
        }
        finally {
            closeConnection(conn);
        }
        return getData();
    }
    // Closes connection with the database.
    public void closeConnection(Connection conn) {
        try {
            conn.close();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //Adds the scroll pane to the table and frame.
    private void createTable(JFrame frame) {
        sp = new JScrollPane(Table01);
        frame.add(sp);
    }


    public static void main(String[] args) {
        //Code to display DatabaseUI.
        JFrame frame = new JFrame("Moodify");
        DatabaseUI ui = new DatabaseUI(frame, "Moodify", true);
        Image image = getImage();
        frame.setIconImage(image);
        frame.setContentPane(ui.DatabaseUIPanel);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.pack();
    }

    public DatabaseUI(JFrame frame, String title, boolean modal) {
        //Code that allows the BackButton to close the DatabaseUI Window and returns to the MainUI.
        super(frame, "Moodify", true);
        BackButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
    }
    // The table was custom created, so it needs to be created here.
    private void createUIComponents() {
        // TODO: place custom component creation code here

        // Sets the column headings
        String[] columnNames = {"Artist", "Album", "Track", "Mood"};

        // Gets the data to be inserted into the table.
        String[][] table = getData();

        // Adds the headings and content to the table.
        Table01 = new JTable(table, columnNames);

        // Adds the scroll pane to the table.
        sp = new JScrollPane(Table01);
    }

    private static Image getImage() {
        // Code to add Moodify icon to taskbar.
        final Toolkit defaultToolkit = Toolkit.getDefaultToolkit();
        final URL imageResource = StageOne.class.getClassLoader().getResource("Images/Logo.png");
        final Image image = defaultToolkit.getImage(imageResource);
        final Taskbar taskbar = Taskbar.getTaskbar();
        try {
            taskbar.setIconImage(image);
        } catch (final UnsupportedOperationException e) {
            System.out.println("The os does not support: 'taskbar.setIconImage'");
        } catch (final SecurityException e) {
            System.out.println("There was a security exception for: 'taskbar.setIconImage'");
        }
        return image;
    }
}