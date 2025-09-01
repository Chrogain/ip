package mel.tasks;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents the deadline task
 */
public class Deadline extends Task {
    protected LocalDate by;


    public Deadline(String description, LocalDate by) {
        super(description);
        this.by = by;

    }


    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)", super.toString(),
                by.format(DateTimeFormatter.ofPattern("MMM d yyyy")));

    }

    @Override
    public String toSaveString() {
        return "D" + super.toSaveString() + " | " + by;

    }

    /**
     * Converts the savedString into a todo task
     *
     * @param savedString
     * @return Task
     */
    public static Task fromSavedString(String savedString) {
        String[] saved = savedString.split(" \\| ");
        Task task = new Deadline(saved[2], LocalDate.parse(saved[3]));
        if (saved[1].equals("1")) {
            task.markAsDone();

        }
        return task;

    }
}
