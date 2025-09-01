package mel.commands;

import mel.apps.Parser;
import mel.apps.Storage;
import mel.apps.Ui;
import mel.exceptions.MelException;
import mel.tasks.TaskList;

public class MarkCommand extends Command {
    private String argument;

    public MarkCommand(String argument) {
        this.argument = argument;

    }

    public void execute(TaskList tasks, Ui ui, Storage storage) throws MelException {
        ui.printOut(tasks.mark(Parser.handleIndex(argument)));

    }

}
