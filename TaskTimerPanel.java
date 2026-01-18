import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class TaskTimerPanel extends JPanel {

    private TimedTask task;
    private JLabel timeLabel;
    private Timer timer;
    private boolean isRunning = false; //not ai
    private JPanel listPanel; //not ai
    private CompletedPanel completedPanel; //not ai

    public TaskTimerPanel(
        TimedTask task,
        TaskManager manager,
        JPanel listPanel,
        CompletedPanel completedPanel) {
        
        this.task = task; //not ai
        this.listPanel = listPanel; //not ai
        this.completedPanel = completedPanel; //not ai
        
        setLayout(new BorderLayout());

        JLabel titleLabel = new JLabel(task.getTitle());
        timeLabel = new JLabel();
        updateLabel();
        
        

        JButton startButton = new JButton("Start");
        JButton pauseButton = new JButton("Pause"); //not ai

        startButton.addActionListener(new ActionListener() { //new start button action listener
        public void actionPerformed(ActionEvent e) {
            if (!isRunning) {
                timer = new Timer(1000, new ActionListener() {
                public void actionPerformed(ActionEvent ev) {
                    task.tick();
                    updateLabel();
                    manager.saveTasks();

                    if (task.isCompleted()) {
                        timer.stop();
                        isRunning = false;

                        listPanel.remove(TaskTimerPanel.this);
                        completedPanel.add(TaskTimerPanel.this);

                        listPanel.revalidate();
                        listPanel.repaint();
                        completedPanel.revalidate();
                        completedPanel.repaint();
                        }
                    }
                });
            timer.start();
            isRunning = true;
                }
            }
        });
        pauseButton.addActionListener(new ActionListener() { //new pause button listener
        public void actionPerformed(ActionEvent e) {
            if (isRunning && timer != null) {
                timer.stop();
                isRunning = false;
                }
            }
        });

        add(titleLabel, BorderLayout.NORTH);
        add(timeLabel, BorderLayout.CENTER);
        
        if (!task.isCompleted()) { //not ai
            JPanel buttonPanel = new JPanel();
            buttonPanel.add(startButton);
            buttonPanel.add(pauseButton);
            add(buttonPanel, BorderLayout.SOUTH);
            }
    }

    private void updateLabel() {
        int remaining = task.getRemainingSeconds();
        timeLabel.setText(task.isCompleted()
                ? "Completed"
                : "Time left: " + remaining + " seconds");
    }
}
