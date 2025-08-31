import apps.Parser;
import apps.Storage;
import apps.Ui;
import exceptions.MelException;
import java.util.Scanner;
import commands.*;
import tasks.*;

import static java.lang.Integer.parseInt;

public class Mel {
    private static Scanner sc = new Scanner(System.in);
    private static TaskList taskList;
    private Storage storage;
    private Ui ui;


    public Mel(String filePath) {
        ui = new Ui(sc);
        storage = new Storage("./data/data.txt");
        try {
            taskList = new TaskList(storage.load(), storage);

        } catch (MelException e) {
            taskList = new TaskList(storage);

        }

    }

    public void run() {
        ui.showGreeting();
        boolean isExit = false;

        while(!isExit) {
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
