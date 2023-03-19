package StageOne;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import java.sql.*;
import java.util.ArrayList;

public class DatabaseUI {
    JPanel DatabaseUIPanel;
    private JPanel DateTimePanel;
    private JLabel DayLabel;
    private JLabel DateLabel;
    private JLabel TimeLabel;
    private JPanel CompanyPanel;
    private JLabel MoodifyLogoLabel;
    private JPanel ListNamePanel;
    private JPanel HeadingsPanel;
    private JButton BackButton;
    private JLabel ArtistHeadingLabel;
    private JLabel AlbumHeadingLabel;
    private JLabel SongHeadingLabel;
    private JLabel MoodHeadingLabel;
    private JLabel LikedHeadingLabel;
    private JTable Table01;

    private JScrollPane sp;



    public Connection dbConnector()
    {
        try {
            Class.forName("org.sqlite.JDBC");
            Connection conn=DriverManager.getConnection("jdbc:sqlite:Database/Moodify.db");
            JOptionPane.showMessageDialog(null,"Connection is successful to database!");
            return conn;

        }catch(Exception e) {
            JOptionPane.showMessageDialog(null,e);
            return null;
        }
    }

    private String[][] getData(){
        ArrayList<String[]> table = new ArrayList<>();
        Connection conn = null;
        String query = "SELECT * from TrackInfo";
        int NUM_COLS = 4;
        try{
            conn = dbConnector();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            int a = 0;
            while(rs.next()){

                System.out.print(a++);
                System.out.println(" " + rs.getString(1));
                String[] dataRow = new String[NUM_COLS];
                dataRow[0] = rs.getString(10);
                dataRow[1] = rs.getString(9);
                dataRow[2] = rs.getString(1);
                dataRow[3] = rs.getString(8);
                table.add(dataRow);
            }
            for(String[] mydata:  table){
                System.out.println("--");
                System.out.print(mydata[0]);
                System.out.print(" | " + mydata[1]);
                System.out.print(" | " + mydata[2]);
                System.out.print(" | " + mydata[3]);
                System.out.println("");
            }

            String[][] fullTable = new String[table.size()][NUM_COLS];
            for(int i = 0; i < table.size(); i++){
                fullTable[i] = table.get(i);
            }


            return fullTable;

        } catch (SQLException sqle){
            sqle.printStackTrace();
        }
        finally {
            closeConnection(conn);
        }
        return getData();
    }

    public void closeConnection(Connection conn){
        try{
            conn.close();

        } catch(SQLException e){
            e.printStackTrace();
        }
    }


    private void createTable(JFrame frame){

        // adding it to JScrollPane
        sp = new JScrollPane(Table01);
        frame.add(sp);
        // Frame Size
        //Table01.setSize(500, 200);
        // Frame Visible = true
        //DatabaseUIPanel.setVisible(true);
    }



    public static void main(String[] args) {
        DatabaseUI ui = new DatabaseUI();
        JFrame frame = new JFrame("Moodify");
        Image image = getImage();
        frame.setIconImage(image);
        frame.setContentPane(ui.DatabaseUIPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setVisible(true);

    }

    public DatabaseUI() {


        BackButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
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