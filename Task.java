public class Task {

    protected String title;
    protected int plannedSeconds;
    protected int elapsedSeconds;
    protected boolean completed;

    public Task(String title, int plannedSeconds, int elapsedSeconds, boolean completed) {
        this.title = title;
        this.plannedSeconds = plannedSeconds;
        this.elapsedSeconds = elapsedSeconds;
        this.completed = completed;
    }

    public String getTitle() {
        return title;
    }

    public boolean isCompleted() {
        return completed;
    }

    public String toFileString() {
        return title + "," + plannedSeconds + "," + elapsedSeconds + "," + completed;
    }
}
