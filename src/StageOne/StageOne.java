package StageOne;

import authorization.PKCE.PKCE.AuthorizationCode;
import authorization.PKCE.PKCE.AuthorizationCodeRefresh;
import authorization.PKCE.PKCE.AuthorizationCodeUri;

import javax.swing.*;
import java.awt.Image;
import java.awt.Taskbar;
import java.awt.Toolkit;
import java.net.URL;
import java.time.*;
import java.awt.*;

public class StageOne extends LoginUI {

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
}