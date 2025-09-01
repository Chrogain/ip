package mel.commands;

import mel.apps.Storage;
import mel.apps.Ui;
import mel.exceptions.MelException;
import mel.tasks.TaskList;

public abstract class Command {
    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws MelException;

    public boolean isExit() {
        return false;

    }

}
