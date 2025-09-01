package mel.apps;

import mel.exceptions.MelException;

import java.util.Scanner;


public class Ui {

    private Scanner sc;

    public Ui (Scanner sc) {
        this.sc = sc;

    }

    public String readCommand() throws MelException {
        System.out.println("Next command:");
        if (sc.hasNext()) {
            return sc.nextLine();

        } else {
            throw new MelException("No more input");

        }

    }

    public void printOut(String input) {
        String line = "_______________________________________________________\n";
        System.out.println(line + " " + input + "\n" + line);

    }

    public void showGreeting() {
        String greeting = "Hello! I'm Mel\n "
                + "What can I do for you?";

        printOut(greeting);

    }

    public void showExit() {
        String exit_message = " Bye! Hope to see you again soon!";
        printOut(exit_message);
    }
}
