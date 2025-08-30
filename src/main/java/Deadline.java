public class Deadline extends Task {
    protected String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;

    }


    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)", super.toString(), this.by);

    }

    @Override
    public String toSaveString() {
        return "D" + super.toSaveString() + " | " + by;

    }

    public static Task fromSavedString(String savedString) {
        String[] saved = savedString.split(" | ");
        Task task = new Deadline(saved[2], saved[3]);
        if (saved[1].equals("1")) {
            task.markAsDone();

        }
        return task;

    }
}
