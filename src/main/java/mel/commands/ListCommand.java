package mel.commands;

import mel.apps.Storage;
import mel.apps.Ui;
import mel.exceptions.MelException;
import mel.tasks.TaskList;

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
