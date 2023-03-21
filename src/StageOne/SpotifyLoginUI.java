package StageOne;

import TheGrid.Fetch;
import authorization.PKCE.PKCE.AuthorizationCode;
import authorization.PKCE.PKCE.AuthorizationCodeRefresh;
import authorization.PKCE.PKCE.AuthorizationCodeUri;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import java.sql.*;

public class SpotifyLoginUI {
    private JPanel LoginUI;
    private JPanel UPPanel;
    private JTextField UserIDTF;
    private JLabel UserIDLbl;
    private JLabel PasswordLabel;
    private JPasswordField CodeTF;
    private JButton LogInButton;
    public JPanel SpotifyLoginUI;

    public static Connection dbConnector()
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
    public static void main(String[] args) {
        JFrame frame = new JFrame("Moodify");
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
        frame.setIconImage(image);
        frame.setContentPane(new SpotifyLoginUI().SpotifyLoginUI);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setVisible(true);
        AuthorizationCodeUri.authorizationCodeUri_Sync();

    }

    public SpotifyLoginUI() {
        LogInButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Connection conn = null;
                    String deleteQuery = "DELETE FROM UInfo";
                    String VAC = "VACUUM";
                    try{
                        conn = dbConnector();
                        Statement stmt = conn.createStatement();
                        stmt.executeQuery(deleteQuery);
                        stmt.executeQuery(VAC);
                        //stmt.executeQuery(StartQuery);

                    } catch (SQLException sqle){
                        sqle.printStackTrace();
                    }

                    Class.forName("org.sqlite.JDBC");
                    Connection connection = DriverManager.getConnection("jdbc:sqlite:Database/Moodify.db");
                    String query = "insert into UInfo (UserID,Code,RefreshTK) values(?,?,?)";
                    PreparedStatement pst = connection.prepareStatement(query);
                    pst.setString(2,CodeTF.getText());
                    pst.setString(1,UserIDTF.getText());
                    pst.execute();
                    pst.setString(3, AuthorizationCode.authorizationCode_Sync());
                    pst.execute();
                    Fetch.Fetch();

                } catch (ClassNotFoundException ex) {
                    throw new RuntimeException(ex);
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
    }


    private void createUIComponents() {
        // TODO: place custom component creation code here
    }
}
