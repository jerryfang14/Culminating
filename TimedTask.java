public class TimedTask extends Task {

    public TimedTask(String title, int plannedSeconds) {
        super(title, plannedSeconds, 0, false);
    }

    public void tick() {
        if (!completed) {
            elapsedSeconds++;
            if (elapsedSeconds >= plannedSeconds) {
                completed = true;
            }
        }
    }

    public int getRemainingSeconds() {
        return plannedSeconds - elapsedSeconds;
    }
}
