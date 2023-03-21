package StageOne;

import org.knowm.xchart.PieChart;
import org.knowm.xchart.PieChartBuilder;
import org.knowm.xchart.PieSeries;
import org.knowm.xchart.style.Styler;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.net.URL;
import java.util.Map;

public class AnalyticsUI {
    private JPanel AnalyticsUIPanel;
    private JPanel DateTimePanel;
    private JLabel DayLabel;
    private JLabel DateLabel;
    private JLabel TimeLabel;
    private JPanel CompanyPanel;
    private JLabel MoodifyLogoLabel;
    private JPanel ListNamePanel;
    private JPanel HeadingsPanel;
    private JButton BackButton;
    private JLabel PieChartLabel;
    public AnalyticsUI() throws IOException {

    }
    
    public static void main(String[] args) throws IOException {
        JFrame frame = new JFrame("Moodify");
        AnalyticsUI ui = new AnalyticsUI();
        Image image = getImage();
        frame.setIconImage(image);
        frame.setContentPane(ui.AnalyticsUIPanel);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.pack();
    }
    public String getExampleChartName() {

        return getClass().getSimpleName() + " - Pie Chart with 4 Slices";
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

    private void createUIComponents() {
        // TODO: place custom component creation code here
    }
}
