package commands;

import apps.Storage;
import apps.Ui;
import exceptions.MelException;
import tasks.Deadline;
import tasks.Event;
import tasks.TaskList;
import tasks.Todo;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class TaskCommand extends Command {

    private String task;
    private String argument;

    public TaskCommand(String argument, String task) throws MelException {
        this.argument = argument;
        this.task = task;
        if (task.equals("T")) {
            if (argument.isEmpty()) {
                throw new MelException.NoArgumentFoundException("todo");

            }

        } else if (task.equals("D")) {
            String[] desc_and_time = argument.split("/by", 2);
            if (desc_and_time.length < 2 || desc_and_time[0].isEmpty() || desc_and_time[1].isEmpty()) {
                throw new MelException.NoArgumentFoundException("deadline");

            }


        } else if (task.equals("E")) {
            String[] desc_and_time = argument.split("/from", 2);
            if (desc_and_time.length < 2 || desc_and_time[0].isEmpty()|| desc_and_time[1].isEmpty()) {
                throw new MelException.NoArgumentFoundException("event");

            }

            String[] fromandto = desc_and_time[1].split("/to", 2);
            if (fromandto.length < 2 || fromandto[0].isEmpty() || fromandto[1].isEmpty()) {
                throw new MelException.NoArgumentFoundException("event");

            }


        } else {
            throw new MelException("Failed to make a task");

        }

    }

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
