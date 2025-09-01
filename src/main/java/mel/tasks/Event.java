package mel.tasks;

/**
 * Represents the event task
 */
public class Event extends Task {

    protected String start;
    protected String end;

    public Event(String description, String start, String end) {
        super(description);
        this.start = start;
        this.end = end;

    }

    @Override
    public String toString() {
        return String.format("[E]%s (from: %s to: %s)", super.toString(), this.start, this.end);

    }

    @Override
    public String toSaveString() {
        return "E" + super.toSaveString() + " | " + start + " | " + end;

    }

    /**
     * Converts the savedString into a event task
     *
     * @param savedString
     * @return Task
     */
    public static Task fromSavedString(String savedString) {
        String[] saved = savedString.split(" \\| ");
        Task task = new Event(saved[2], saved[3], saved[4]);
        if (saved[1].equals("1")) {
            task.markAsDone();

        }
        return task;

    }

}
