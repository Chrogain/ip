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



}
