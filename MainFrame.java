import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {

    public MainFrame() {
        setTitle("Study Planner");
        setSize(600, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JTabbedPane tabs = new JTabbedPane();

        CompletedPanel completedPanel = new CompletedPanel();
        PlannerPanel plannerPanel = new PlannerPanel(completedPanel);
        
        tabs.addTab("Active Tasks", plannerPanel);
        tabs.addTab("Completed Tasks", completedPanel);

        add(tabs, BorderLayout.CENTER);
    }
}
