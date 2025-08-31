package commands;

import apps.Parser;
import apps.Storage;
import apps.Ui;
import exceptions.MelException;
import tasks.TaskList;

public class DeleteCommand extends Command {

    private String argument;

    public DeleteCommand(String argument) {
        this.argument = argument;

    }

    public void execute(TaskList tasks, Ui ui, Storage storage) throws MelException {
        ui.printOut(tasks.remove(Parser.handleIndex(argument)));

    }

}
