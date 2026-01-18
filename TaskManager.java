import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class TaskManager {

    private static final String FILE = "tasks.txt";
    private List<Task> tasks = new ArrayList<>();

    public void loadTasks() {
    tasks.clear();
    try (BufferedReader br = new BufferedReader(new FileReader(FILE))) {
        String line;
        while ((line = br.readLine()) != null) {
            String[] p = line.split(",");

            TimedTask task = new TimedTask(
                    p[0],
                    Integer.parseInt(p[1])
            );

            task.elapsedSeconds = Integer.parseInt(p[2]);
            task.completed = Boolean.parseBoolean(p[3]);

            tasks.add(task);
        }
    } catch (IOException e) {
        e.printStackTrace();
    }
}

    public void saveTasks() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILE))) {
            for (Task t : tasks) {
                bw.write(t.toFileString());
                bw.newLine();
            }
        } catch (IOException e) {}
    }

    public void addTask(Task task) {
        tasks.add(task);
        saveTasks();
    }

    public List<Task> getTasks() {
        return tasks;
    }
}
