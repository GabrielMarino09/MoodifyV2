package StageOne;

import javax.swing.*;

public class LoginUI {
    public JPanel LoginUI;
    private JTextField UsernameTextField;
    private JLabel UsernameLabel;
    private JLabel PasswordLabel;
    private JPasswordField PasswordField;
    private JCheckBox Checkbox;
    private JButton LogInButton;
    private JLabel MoodifyLogoLabel;
    private JLabel SloganLabel;
    private JPanel CompanyPanel;
    private JPanel UPPanel;
    private JPanel BoxButtonPanel;

    public static void main(String[] args) {
        JFrame frame = new JFrame("Moodify");
        frame.setContentPane(new LoginUI().LoginUI);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setVisible(true);
        frame.setVisible(true);
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
    }
}
