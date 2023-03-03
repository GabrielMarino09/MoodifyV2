//======================================================================================================================
//Initial Package

package MoodifyBeta;

//======================================================================================================================
//Imports:

//AWT:

import java.awt.*;

//Swing:

import javax.swing.*;

//======================================================================================================================
//Beginning of code:

public class MoodifyLogin extends JFrame{

    public JPanel JPanel_Login;

    private JLabel Username_Label;
    private JLabel Moodify_Label;
    private JLabel Password_Label;
    private JTextField Username_Field;
    private JPasswordField Password_Field;
    private JCheckBox TOS_CheckBox;
    private JButton Log_In_Button;



    public static void main(String[] args){
        JFrame frame = new JFrame("MoodifyLogin");
        frame.setContentPane(new MoodifyLogin().JPanel_Login);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }


    private void createUIComponents() {
        // TODO: place custom component creation code here
    }
}
