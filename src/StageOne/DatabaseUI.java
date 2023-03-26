package StageOne;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.net.URL;
import java.sql.*;
import java.util.ArrayList;

public class DatabaseUI extends JDialog {
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


    public Connection dbConnector() {
        try {
            Class.forName("org.sqlite.JDBC");
            Connection conn = DriverManager.getConnection("jdbc:sqlite:Database/Moodify.db");
            return conn;

        } catch (Exception e) {
            return null;
        }
    }

    private String[][] getData() {
        ArrayList<String[]> table = new ArrayList<>();
        Connection conn = null;
        String query = "SELECT * from TrackInfo";
        int NUM_COLS = 4;
        try {
            conn = dbConnector();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            int a = 0;
            while (rs.next()) {

                String[] dataRow = new String[NUM_COLS];
                dataRow[0] = rs.getString(10);
                dataRow[1] = rs.getString(9);
                dataRow[2] = rs.getString(1);
                dataRow[3] = rs.getString(8);
                table.add(dataRow);
            }

            String[][] fullTable = new String[table.size()][NUM_COLS];
            for (int i = 0; i < table.size(); i++) {
                fullTable[i] = table.get(i);
            }


            return fullTable;

        } catch (SQLException sqle) {
            sqle.printStackTrace();
        } finally {
            closeConnection(conn);
        }
        return getData();
    }

    public void closeConnection(Connection conn) {
        try {
            conn.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    private void createTable(JFrame frame) {
        sp = new JScrollPane(Table01);
        frame.add(sp);
    }



    public static void main(String[] args) {
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
        super(frame, "Moodify", true);
        BackButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
    }




    private void createUIComponents() {
        // TODO: place custom component creation code here

        String[] columnNames = {"Artist", "Album", "Track", "Mood"};
        String[][] table = getData();
        Table01 = new JTable(table, columnNames);
        sp = new JScrollPane(Table01);
    }

    private static Image getImage() {
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