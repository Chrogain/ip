package tasks;

import apps.Storage;
import exceptions.MelException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TaskList {
    private List<Task> taskList;
    private Storage storage;

    public TaskList(Storage storage) {
        this.storage = storage;
        this.taskList = new ArrayList<>();

    }

    public TaskList(String[] savedStrings, Storage storage) throws MelException {
        this.storage = storage;
        this.taskList = new ArrayList<>();
        for (String s : savedStrings) {
            taskList.add(Task.fromSavedString(s));

        }

    }

    public String add(Task task) throws MelException {
        taskList.add(task);
        this.update();
        String output = String.format("Got it. I've added this task:\n  %s\n Now you have %d task(s) in the list.", task.toString(), taskList.size());
        return output;

    }

    public String remove(int index) throws MelException {
        if (!validIndex(index)) {
            throw new MelException.InvalidIndexException(String.format("It is out of range! Please put a number from 1 to %d.", taskList.size()));

        }
        Task task = taskList.remove(index);
        this.update();
        String output = String.format("OK, I've removed this task:\n  %s\nNow you have %d tasks in the list.", task.toString(), taskList.size());
        return output;

    }

    public int size() {
        return taskList.size();

    }

    public Task get(int index) {
        return taskList.get(index);

    }

    public String mark(int index) throws MelException {
        if (!validIndex(index)) {
            throw new MelException.InvalidIndexException(String.format("It is out of range! Please put a number from 1 to %d.", taskList.size()));

        }
        String s =  taskList.get(index).markAsDone();
        this.update();
        return s;

    }

    public String unmark(int index) throws MelException {
        if (!validIndex(index)) {
            throw new MelException.InvalidIndexException(String.format("It is out of range! Please put a number from 1 to %d.", taskList.size()));
        }
        String s = taskList.get(index).undo();
        this.update();
        return s;

    }

    public boolean validIndex(int index) throws MelException.EmptyListException {
        if (taskList.isEmpty()) throw new MelException.EmptyListException();
        return index >= 0 && index < taskList.size();

    }

    public void update() throws MelException {
        List<String> savedStrings = new ArrayList<>();
        for (Task task : taskList) {
            savedStrings.add(task.toSaveString());

        }

        try {
            storage.save(savedStrings.toArray(String[]::new));

        } catch (IOException e) {
            throw new MelException("File is not saving!");

        }
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
