//SpotifyLoginUI is a part of the package named StageOne.
package StageOne;

//Imports the other classes.
import TheGrid.Fetch;
import authorization.PKCE.PKCE.AuthorizationCode;
import authorization.PKCE.PKCE.AuthorizationCodeUri;

//Imports the other used libraries.
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import java.sql.*;

public class SpotifyLoginUI {
    //Establishes the connection between the form for the SpotifyLoginUI and its contents, so things can be altered, and actions can be executed.
    private JPanel LoginUI;
    private JPanel UPPanel;
    private JTextField UserIDTF;
    private JLabel UserIDLbl;
    private JLabel PasswordLabel;
    private JPasswordField CodeTF;
    private JButton LogInButton;
    public JPanel SpotifyLoginUI;
    private JFrame frame;

    //Returns the JFrame frame, so it can be used by all parts in this class.
    public JFrame getFrame() {
        return frame;
    }

    // Establishes connection with the Moodify database.
    public static Connection dbConnector() {
        try {
            Class.forName("org.sqlite.JDBC");
            Connection conn=DriverManager.getConnection("jdbc:sqlite:Database/Moodify.db");
            return conn;
        }
        catch(Exception e) {
            return null;
        }
    }

    public static void main() {
        // Code to add Moodify icon to taskbar.
        final Toolkit defaultToolkit = Toolkit.getDefaultToolkit();
        final URL imageResource = StageOne.class.getClassLoader().getResource("Images/Logo.png");
        final Image image = defaultToolkit.getImage(imageResource);
        final Taskbar taskbar = Taskbar.getTaskbar();
        try {
            taskbar.setIconImage(image);
        }
        catch (final UnsupportedOperationException e) {
            System.out.println("The os does not support: 'taskbar.setIconImage'");
        }
        catch (final SecurityException e) {
            System.out.println("There was a security exception for: 'taskbar.setIconImage'");
        }

        //Code to display SpotifyUI.
        SpotifyLoginUI SpotifyLogin = new SpotifyLoginUI();
        JFrame frame = SpotifyLogin.getFrame();
        frame.setIconImage(image);
        frame.setContentPane(SpotifyLogin.SpotifyLoginUI);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setVisible(true);

        // Runs the autorizationCodeUri_Sync from the AuthorizationCodeUri:
        AuthorizationCodeUri.authorizationCodeUri_Sync();
    }

    public SpotifyLoginUI() {
        // Retrieves the frame previously created, so that code in this class can interact with it.
        this.frame = new JFrame("Moodify");

        // Defines the actions that will occur when the  LogInButton is clicked.
        LogInButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    // Deletes all records of the database and uses the vacuum function to delete null cells.
                    Connection conn = null;
                    String deleteQuery = "DELETE FROM UInfo";
                    String VAC = "VACUUM";
                    try{
                        conn = dbConnector();
                        Statement stmt = conn.createStatement();
                        stmt.executeQuery(deleteQuery);
                        stmt.executeQuery(VAC);
                    }
                    catch (SQLException sqle){
                        sqle.printStackTrace();
                    }

                    // Code to add data to the UInfo table in the Moodify Database
                    Class.forName("org.sqlite.JDBC");
                    Connection connection = DriverManager.getConnection("jdbc:sqlite:Database/Moodify.db");
                    String query = "insert into UInfo (UserID,Code,RefreshTK) values(?,?,?)";
                    PreparedStatement pst = connection.prepareStatement(query);

                    //Adds the code from the text field and password field to the database.
                    pst.setString(2,CodeTF.getText());
                    pst.setString(1,UserIDTF.getText());
                    pst.execute();
                    // Executes the code from AuthorizationCode to generate a refresh token.
                    pst.setString(3, AuthorizationCode.authorizationCode_Sync());
                    pst.execute();

                    //Waits 5 second, waiting for the refresh token to be generated.
                    Thread.sleep(5000);

                    // closes this frame.
                    frame.dispose();

                    // Starts to run the code from the Fetch class.
                    Fetch.Fetch();
                }

                // Standard exceptions if a class is not found.
                catch (ClassNotFoundException ex) {
                    throw new RuntimeException(ex);
                }
                // Standard exceptions if the database has issues.
                catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
                // Standard exceptions for Interruptions.
                catch (InterruptedException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
    }

    //Code automatically generated by IntelliJ IDEA, not used for current class.
    private void createUIComponents() {
        // TODO: place custom component creation code here
    }
}
