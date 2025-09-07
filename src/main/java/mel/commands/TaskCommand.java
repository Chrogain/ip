package mel.commands;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import mel.apps.Storage;
import mel.apps.Ui;

import mel.exceptions.MelException;

import mel.tasks.Deadline;
import mel.tasks.Event;
import mel.tasks.TaskList;
import mel.tasks.Todo;


/**
 * Represents the different types of task commands as one class such as
 * deadline, todo and event
 */
public class TaskCommand extends Command {

    private String task;
    private String argument;

    /**
     * Returns the corresponding task as a Command.
     * Throws an exception when the argument for the corresponding task is incorrect.
     * @param argument
     * @param task
     * @throws MelException
     */
    public TaskCommand(String argument, String task) throws MelException {
        this.argument = argument;
        this.task = task;
        if (task.equals("T")) {
            if (argument.isEmpty()) {
                throw new MelException.NoArgumentFoundException("todo");

            }

        } else if (task.equals("D")) {
            String[] descAndTime = argument.split("/by", 2);
            if (descAndTime.length < 2
                    || descAndTime[0].isEmpty()
                    || descAndTime[1].isEmpty()) {
                throw new MelException.NoArgumentFoundException("deadline");

            }


        } else if (task.equals("E")) {
            String[] descAndTime = argument.split("/from", 2);
            if (descAndTime.length < 2
                    || descAndTime[0].isEmpty()
                    || descAndTime[1].isEmpty()) {
                throw new MelException.NoArgumentFoundException("event");

            }

            String[] fromAndTo = descAndTime[1].split("/to", 2);
            if (fromAndTo.length < 2
                    || fromAndTo[0].isEmpty()
                    || fromAndTo[1].isEmpty()) {
                throw new MelException.NoArgumentFoundException("event");

            }


        } else {
            throw new MelException("Failed to make a task");

        }

    }

    /**
     * Prints out the corresponding output for each task
     * @param tasks
     * @param ui
     * @param storage
     * @throws MelException
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) throws MelException {
        if (task.equals("T")) {
            ui.printOut(tasks.add(new Todo(argument)));


        } else if (task.equals("D")) {
            String[] desc_and_time = argument.split("/by", 2);

            String desc = desc_and_time[0].trim();
            String by = desc_and_time[1].trim();
            try {
                ui.printOut(tasks.add(new Deadline(desc, LocalDate.parse(by))));
            } catch (DateTimeParseException e) {
                throw new MelException("Incorrect date format!");

            }
        } else if (task.equals("E")) {
            String[] desc_and_time = argument.split("/from", 2);
            String[] fromandto = desc_and_time[1].split("/to", 2);

            String desc = desc_and_time[0].trim();
            String from = fromandto[0].trim();
            String to = fromandto[1].trim();

            ui.printOut(tasks.add(new Event(desc, from, to)));

        }

    }

}
