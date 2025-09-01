package mel.tasks;

import mel.exceptions.MelException;

public abstract class Task {
    protected String description;
    protected boolean isDone;

    public Task() {
        this.isDone = false;
    }
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " ");

    }

    public String markAsDone() {
        this.isDone = true;
        String output = String.format("Nice! I've marked this task as done:\n  %s", this.toString());
        return output;

    }

    public String undo() {
        this.isDone = false;
        String output = String.format("OK, I've marked this task as not done yet:\n  %s", this.toString());
        return output;
    }

    public String toString() {
        return "[" + getStatusIcon() + "] " + this.description;
    }

    public String toSaveString() {
        String done = isDone ? "1" : "0";
        return " | " + done + " | " + description;

    }

    public static Task fromSavedString(String savedString) throws MelException {
        String[] saved = savedString.split(" | ");
        if (saved.length < 1) {
            throw new MelException("Saved line is empty!");

        } else if (saved[0].equals("T")) {
            return Todo.fromSavedString(savedString);

        } else if (saved[0].equals("D")) {
            return Deadline.fromSavedString(savedString);

        } else if (saved[0].equals("E")) {
            return Event.fromSavedString(savedString);

        } else {
            throw new MelException("Error reading saved data!");

        }
    }


}
