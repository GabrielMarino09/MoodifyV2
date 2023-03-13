package StageOne;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import java.sql.*;

public class DatabaseUI {
    JPanel DatabaseUI;
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
    private JLabel AA;
    private JLabel AB;
    private JLabel AC;
    private JLabel AD;
    private JLabel BA;
    private JLabel BB;
    private JLabel BC;
    private JLabel BD;
    private JLabel CA;
    private JLabel CB;
    private JLabel CC;
    private JLabel CD;
    private JLabel DA;
    private JLabel DB;
    private JLabel DC;
    private JLabel DE;
    private JLabel EA;
    private JLabel EB;
    private JLabel EC;
    private JLabel ED;
    private JLabel FA;
    private JLabel FB;
    private JLabel FC;
    private JLabel FD;
    private JLabel GA;
    private JLabel GB;
    private JLabel GC;
    private JLabel GD;
    private JLabel HA;
    private JLabel HB;
    private JLabel HC;
    private JLabel HD;
    private JLabel IA;
    private JLabel IB;
    private JLabel IC;
    private JLabel ID;
    private JLabel JA;
    private JLabel JB;
    private JLabel JC;
    private JLabel JD;
    private JLabel KA;
    private JLabel KB;
    private JLabel KC;
    private JLabel KD;
    private JLabel LA;
    private JLabel LB;
    private JLabel LC;
    private JLabel LD;
    private JLabel MA;
    private JLabel MB;
    private JLabel MC;
    private JLabel MD;
    private JLabel NA;
    private JLabel NB;
    private JLabel NC;
    private JLabel ND;
    private JLabel OA;
    private JLabel OB;
    private JLabel OC;
    private JLabel OD;
    private JLabel PA;
    private JLabel PB;
    private JLabel PC;
    private JLabel PD;
    private JLabel QA;
    private JLabel QB;
    private JLabel QC;
    private JLabel QD;
    private JLabel RA;
    private JLabel RB;
    private JLabel RC;
    private JLabel RD;
    private JLabel SA;
    private JLabel SB;
    private JLabel SC;
    private JLabel SD;
    private JLabel TA;
    private JLabel TB;
    private JLabel TC;
    private JLabel TD;
    private JLabel UA;
    private JLabel UB;
    private JLabel UC;
    private JLabel UD;
    private JLabel VA;
    private JLabel VB;
    private JLabel VC;
    private JLabel VD;


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

    public DatabaseUI() {
        BackButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DatabaseUI.setVisible(false);
            }
        });
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
    }
}