package commands;

import apps.Parser;
import apps.Storage;
import apps.Ui;
import exceptions.MelException;
import tasks.TaskList;

public class UnmarkCommand extends Command {
    private String argument;

    public UnmarkCommand(String argument) {
        this.argument = argument;

    }

    public void execute(TaskList tasks, Ui ui, Storage storage) throws MelException {
        ui.printOut(tasks.unmark(Parser.handleIndex(argument)));


    }

}
