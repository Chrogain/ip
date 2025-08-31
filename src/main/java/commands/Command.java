package commands;

import apps.Storage;
import apps.Ui;
import exceptions.MelException;
import tasks.TaskList;

public abstract class Command {
    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws MelException;

    public boolean isExit() {
        return false;

    }

}
