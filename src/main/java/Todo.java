public class Todo extends Task {

    public Todo(String description) {
        super(description);

    }

    @Override
    public String toString() {
        String output = String.format("[T]%s", super.toString());
        return output;

    }

    @Override
    public String toSaveString() {
        return "T" + super.toSaveString();

    }

    public static Task fromSavedString(String savedString) {
        String[] saved = savedString.split(" \\| ");

        Task task = new Todo(saved[2]);
        if (saved[1].equals("1")) {
            task.markAsDone();

        }
        return task;

    }

}
