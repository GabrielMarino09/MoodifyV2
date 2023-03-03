package StageOne;

import javax.swing.*;
import java.awt.*;
import java.net.URL;

public class SpotifyLoginUI {
    private JPanel LoginUI;
    private JPanel UPPanel;
    private JTextField UsernameTextField;
    private JLabel UsernameLabel;
    private JLabel PasswordLabel;
    private JPasswordField PasswordField;
    private JPanel BoxButtonPanel;
    private JButton LogInButton;
    private JPanel SpotifyLoginUI;

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
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
    }
}
