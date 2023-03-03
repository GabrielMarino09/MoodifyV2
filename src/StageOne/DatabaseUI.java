package StageOne;

import javax.swing.*;
import java.awt.*;
import java.net.URL;
public class DatabaseUI {
    private JPanel DatabaseUI;
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
        frame.setContentPane(new DatabaseUI().DatabaseUI);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setVisible(true);
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
    }
}