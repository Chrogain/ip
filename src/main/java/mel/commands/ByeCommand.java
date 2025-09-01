package mel.commands;

import mel.exceptions.MelException;

import mel.tasks.TaskList;

import mel.apps.Ui;
import mel.apps.Storage;


public class ByeCommand extends Command {

    public ByeCommand(String argument) throws MelException.ExtraArgumentException {
        if (argument != "") {
            throw new MelException.ExtraArgumentException("bye");

        }

    }

    public void execute(TaskList tasks, Ui ui, Storage storage) {
        return;

    }

    @Override
    public boolean isExit() {
        return true;

    }


}
