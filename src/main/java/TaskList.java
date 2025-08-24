import java.util.ArrayList;
import java.util.List;

public class TaskList {
    private List<Task> taskList;

    public TaskList() {
        this.taskList = new ArrayList<>();

    }

    public String add(Task task) {
        taskList.add(task);
        String output = String.format("Got it. I've added this task:\n  %s\n Now you have %d task(s) in the list.", task.toString(), taskList.size());
        return output;

    }

    public String remove(int index) {
        Task task = taskList.remove(index);
        String output = String.format("OK, I've removed this task:\n  %s\nNow you have %d tasks in the list.", task.toString(), taskList.size());
        return output;

    }

    public int size() {
        return taskList.size();

    }

    public Task get(int index) {
        return taskList.get(index);

    }

    public String mark(int index) {
        return taskList.get(index).markAsDone();

    }

    public String unmark(int index) {
        return taskList.get(index).undo();

    }

    public boolean validIndex(int index) throws MelException.EmptyListException {
        if (taskList.isEmpty()) throw new MelException.EmptyListException();
        return index >= 0 && index < taskList.size();

    }

    @Override
    public String toString() {
        if (taskList.isEmpty()) {
            return "The list is empty!";

        }
        int index = 1;
        StringBuilder output = new StringBuilder();
        output.append("Here are the tasks in your list:\n ");
        for (Task task : taskList) {
            if (index > 1) {
                output.append("\n ");

            }

            String taskString = String.format("%d.%s", index, task.toString());
            output.append(taskString);
            index++;

        }

        return output.toString();
    }

}
