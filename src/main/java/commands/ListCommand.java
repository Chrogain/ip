package commands;

import apps.Storage;
import apps.Ui;
import exceptions.MelException;
import tasks.TaskList;

public class ListCommand extends Command {

    public ListCommand(String argument) throws MelException.ExtraArgumentException {
        if (argument != "") {
            throw new MelException.ExtraArgumentException("list");

        }
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.printOut(tasks.toString());
        return;

    }

}
