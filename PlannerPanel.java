import javax.swing.*;
import java.awt.*;

public class PlannerPanel extends JPanel {

    private TaskManager manager = new TaskManager();
    private JPanel listPanel;
    
    private CompletedPanel completedPanel; //not ai

    public PlannerPanel(CompletedPanel completedPanel) { //not ai
        setLayout(new BorderLayout());
        this.completedPanel = completedPanel;// no ai

        JTextField titleField = new JTextField();
        JTextField timeField = new JTextField();
        JButton addButton = new JButton("Add Task");

        JPanel input = new JPanel(new GridLayout(1, 3));
        input.add(titleField);
        input.add(timeField);
        input.add(addButton);

        listPanel = new JPanel();
        listPanel.setLayout(new BoxLayout(listPanel, BoxLayout.Y_AXIS));

        add(input, BorderLayout.NORTH);
        add(new JScrollPane(listPanel), BorderLayout.CENTER);

        manager.loadTasks();
        loadTasksToUI();

        addButton.addActionListener(e -> { //not ai method
            String title = titleField.getText();
            int seconds = Integer.parseInt(timeField.getText());

            TimedTask task = new TimedTask(title, seconds);
            manager.addTask(task);

            TaskTimerPanel panel =
                new TaskTimerPanel(task, manager, listPanel, completedPanel);

            listPanel.add(panel);

            revalidate();
            titleField.setText("");
            timeField.setText("");
        });
    }

    private void loadTasksToUI() {
    for (Task t : manager.getTasks()) {

        if (t instanceof TimedTask task) {

            TaskTimerPanel panel =
                new TaskTimerPanel(task, manager, listPanel, completedPanel);

            if (task.isCompleted()) {
                completedPanel.add(panel);
            } else {
                listPanel.add(panel);
            }
        }
    }
    revalidate();
    repaint();
    completedPanel.revalidate();
    completedPanel.repaint(); //mnot ai
}
}
