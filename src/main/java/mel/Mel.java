package mel;

import java.util.Scanner;

import mel.apps.Parser;
import mel.apps.Storage;
import mel.apps.Ui;

import mel.commands.Command;

import mel.exceptions.MelException;

import mel.tasks.TaskList;


/**
 * This is the Mel Chatbot
 */
public class Mel {
    private static Scanner sc = new Scanner(System.in);
    private static TaskList taskList;
    private Storage storage;
    private Ui ui;

    /**
     * Returns the Mel object with the filePath as the parameter for where it should store
     * the data.
     * @param filePath
     */
    public Mel(String filePath) {
        ui = new Ui(sc);
        storage = new Storage("./data/data.txt");
        try {
            taskList = new TaskList(storage.load(), storage);

        } catch (MelException e) {
            taskList = new TaskList(storage);

        }

    }

    /**
     * Initialises the Mel Chatbot
     */
    public void run() {
        ui.showGreeting();
        boolean isExit = false;

        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                Command c = Parser.parse(fullCommand);
                c.execute(taskList, ui, storage);
                isExit = c.isExit();

            } catch (MelException e) {
                ui.printOut(e.getMessage());

            }
        }

        ui.showExit();

    }

    public static void main(String[] args) {
        new Mel("data/tasks.txt").run();

    }

}
